/* LITERATURA KOJA JE POSLUŽILA KAO POMOĆ ZA IZRADU OVE AKTIVNOSTI
*
*  Blog post 1:         http://androidmastermind.blogspot.hr/2016/06/android-google-maps-with-nearyby-places.html
*  Blog post 2:         http://blog.teamtreehouse.com/beginners-guide-location-android
*  OkHttp library:      http://square.github.io/okhttp/
*  Google places API:   https://developers.google.com/places/
*
******************************************************************************************************************/

package com.example.monique.hrpaknjiga.activities;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.constants.Constants;
import com.example.monique.hrpaknjiga.models.NearbyPlace;
import com.example.monique.hrpaknjiga.models.NearbyPlacesInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LocationActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    // KONSTANTE
    public static final String TAG = LocationActivity.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;      //za mehanizme otklanjanja pogrešaka.
    private static final int PERMISSION_REQUEST_CODE = 1;                       //za dobivanje permissiona

    private static final String PLACES_URL =  "https://maps.googleapis.com/maps/api/place/search/json?"; //link za traženje okolnih mjesta

    private GoogleMap mGoogleMap;                     //Google karta
    private MapFragment mMapFragment;           //Fragment koji sadrži kartu

    private GoogleApiClient mGoogleApiClient;   //GoogleAPI klijent olakšava upotrebu Google Play usluga
    private LocationRequest mLocationRequest;

    private Geocoder mGeocoder;                //za dobivanje imena mjesta
    private String locationText;

    private NearbyPlacesInfo mNearbyLibraries;
    private NearbyPlacesInfo mNearbyBookstores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        //provjeri jesu li omogućene GooglePlay usluge
        if (!isGooglePlayServicesAvailable()) {
            return;
        }

        this.mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        this.mMapFragment.getMapAsync(this);


        if (Geocoder.isPresent()) {
            this.mGeocoder = new Geocoder(this);
        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)  //stvori novi GoogleApiClient objekt koristeći Builder
                .addConnectionCallbacks(this)                 //trenutna klasa će se pobrinuti za konekciju, taj se posao treba raditi u pozadini
                .addOnConnectionFailedListener(this)          //API za lokacijske usluge
                .addApi(LocationServices.API)                 //build
                .build();

        mLocationRequest = LocationRequest.create()                    //kreiraj objekt za zahtjevanje lokacije
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)  //postavi prioritet visoke točnosti
                .setInterval(300*1000)                                 //  3 minute u milisekundama je interval između aktivnih updatea lokacije
                .setFastestInterval(30*1000);                          // 1 sekunda, najkraći interval u kojem će aplikaciji dobiti update - uzeti u obzir ostale aplikacije koje možda zahtjevaju lokaciju

    }

    /* Koristi se onResume zbog životnog ciklusa aktivnosti - aplikacija
     * može biti pauzirana u bilo kojem trenutku (poruka, poziv) zato želimo
     * pauzirati dok se to događa i onda se vratiti u aktivnost
     * onResume se poziva nakon onCreate tako da nakon što je veza uspostvaljena
     * moguće je pristupiti kolaciji kada god je aktivnost vidljiva */

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    /* Kad god se koristi onResume metoda, treba razmisliti o pripadnoj onPause metodi
     * To nije uvijek potrebno, ali primjerice omogućuje prekid veze kad je aktivnosti
     * pauzirana i njeno ponovno uspostavljanje pri povratku u aktivnost.*/

    @Override
    protected void onPause() {
        super.onPause();

        //Prije prekida veze, provjeriti postoji li veza uopće
        if (mGoogleApiClient.isConnected()) {

            //Ukloni UPDATE nakon pozivanja
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

        //Quick fix code
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            return;
        }
        //Dohvati posljednju poznatu lokaciju i imaj na umu da bi mogla biti NULL
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            //Ovaj kod će zahtjevati UPDATE samo ako nije poznata posljednja lokacija.
            //Najprije uključiti GPS, a zatim pokrenuti aplikaciju.
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            handleNewLocation(location);
        }
        Log.i(TAG, "Upostavljena veza s lokacijskim uslugama.");
    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Nije uspostavljena veza s lokacijskim uslugama. Pokušajte ponovno.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {

                // Pokreni aktivnost koja će pokušati riješiti problem
                // Ugrađeni mehanizmi uklanjanja pogrešaka od strane Google Play usluga
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {

            //U slučaju da nije pronađeno rješenje korisniku možda prikazati alert s porukom.
            Log.i(TAG, "Nije uspostavljena veza s lokacijskim uslugama: " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //Poziva se svaki put kada je detektirana promjena lokacije
        //Metoda koja služi za rad s lokacijom
        handleNewLocation(location);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(LocationActivity.this, "Permission Granted, Now you can access location data.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LocationActivity.this, "Permission Denied, You cannot access location data.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /****** MOJE METODE ******/

    //Metoda za rad s dohvaćenom lokacijom. Sve najvažnije nalazi se OVDJE.
    private void handleNewLocation(Location location) {

        locationText = "Nepoznata lokacija.";  //ZA SLUČAJ DA NEGDJE ZATREBA PRIKAZ
        double lat = 0;
        double lng = 0;

        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            LatLng latLng = new LatLng(lat, lng);
            locationText =
                    "Geografska širina: " + lat + "\n" +
                    "Geografska dužina: " + lng;
            setUpMap(latLng);
        }

        if (this.mGeocoder != null && location != null) {
            try {
                ArrayList<Address> nearbyAddresses =
                        (ArrayList<Address>) this.mGeocoder.getFromLocation(lat, lng, 1);
                if (null != nearbyAddresses && nearbyAddresses.size() > 0) {
                    Address myAddress = nearbyAddresses.get(0);
                    locationText += "\n" +
                            "Država: " + myAddress.getCountryName() + "\n" +
                            "Mjesto: " + myAddress.getLocality() + "\n" +
                            "Adresa: " + myAddress.getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //Pozovi metodu za dobivanje okolnih mjesta
        loadNearbyBookPlaces(lat, lng, Constants.TYPE_LIBRARY);
        loadNearbyBookPlaces(lat,lng, Constants.TYPE_BOOKSTORE);


    }

    private void loadNearbyBookPlaces (double lat, double lng, final String type) {
        if (isNetworkAvailable()) {
            //API URL
            StringBuilder googlePlacesUrl =
                new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                googlePlacesUrl.append("location=").append(lat).append(",").append(lng);
                googlePlacesUrl.append("&radius=").append(Constants.PROXIMITY_RADIUS);
                googlePlacesUrl.append("&types=").append(type);
                googlePlacesUrl.append("&sensor=true");
                googlePlacesUrl.append("&key=" + Constants.GOOGLE_BROWSER_API_KEY);

            Log.d(TAG, "API URL - unesi u Browser za cjelokupan pregled: " + googlePlacesUrl.toString());

            //Postavi OkHttpClient za obradu HTTP zahtjeva
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(googlePlacesUrl.toString())
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                //U slučaju neuspješnog odziva
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(LocationActivity.this, "Mreža nije dostupna.", Toast.LENGTH_SHORT).show();
                }

                //U slučaju uspješnog odziva
                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            //Popuni niz ovisno o tome je li mjesto knjižara ili knjižnica
                            if (type.equals(Constants.TYPE_BOOKSTORE)) {
                                mNearbyBookstores = parseLocationResults(jsonData, type);
                                Log.d(TAG, "BROJ KNJIŽARA:" + mNearbyBookstores.getLength());
                            } else if (type.equals(Constants.TYPE_LIBRARY)) {
                                mNearbyLibraries = parseLocationResults(jsonData, type);
                                Log.d(TAG, "BROJ KNJIŽNICA:" + mNearbyLibraries.getLength());
                            }
                        } else {
                            Toast.makeText(LocationActivity.this, "Neuspješno dohvaćanje podataka.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            Toast.makeText(LocationActivity.this, "Mreža nije dostupna. Pokrenite aplikaciju nakon uspostave mreže.", Toast.LENGTH_SHORT).show();
        }

    }
    //Parsiraj podatke u objekt NearbyPlaces
    private NearbyPlacesInfo parseLocationResults (String jsonData, String type) throws JSONException {
        NearbyPlacesInfo places = new NearbyPlacesInfo();
        places.setmNearbyPlaces(getLocationResults(jsonData,type));
        Log.d(TAG, "DULJINA NIZA: " + places.getLength());
        return  places;
    }

    //Dohvati JSON rezultate
    private NearbyPlace[] getLocationResults (String jsonData, String type) throws JSONException {

        JSONObject result = new JSONObject(jsonData);
        JSONArray places = result.getJSONArray(Constants.RESULTS);

        NearbyPlace[] nearPlaces = new NearbyPlace[places.length()];

        for (int i = 0; i < places.length(); i++) {
            JSONObject jsonPlace = places.getJSONObject(i);

            final NearbyPlace place = new NearbyPlace();

            //Dohvaćanje json objekata i postavljanje atributa objekta tipa "NearbyPlace"
            place.setId(jsonPlace.getString(Constants.ID));
            if(!jsonPlace.isNull(Constants.PLACE_ID)) {
                place.setPlaceId(jsonPlace.getString(Constants.PLACE_ID));
            } else {
                place.setPlaceId("Nepoznato");
            }
            place.setType(type);
            if(!jsonPlace.isNull(Constants.NAME)) {
                place.setPlaceName(jsonPlace.getString(Constants.NAME));
            } else {
                place.setPlaceId("Nepoznato");
            }
            place.setLatitude(Double.parseDouble(jsonPlace.getJSONObject(Constants.GEOMETRY)
                    .getJSONObject(Constants.LOCATION)
                    .getString(Constants.LATITUDE)));
            place.setLongitude(Double.parseDouble(jsonPlace.getJSONObject(Constants.GEOMETRY)
                    .getJSONObject(Constants.LOCATION)
                    .getString(Constants.LONGITUDE)));
            if(!jsonPlace.isNull(Constants.NAME)) {
                place.setPlaceVicinity(jsonPlace.getString(Constants.VICINITY));
            } else {
                place.setPlaceVicinity("Nepoznato");
            }
            place.setPlaceIcon(jsonPlace.getString(Constants.ICON));

            nearPlaces[i] = place;

            final LatLng location = new LatLng(place.getLatitude(),place.getLongitude());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setPlaceMarker(location, place);
                }
            });

            Log.i(TAG, "Place NAME: " + nearPlaces[i].getPlaceName());
        }
        return nearPlaces;
    }

    //Provjeri dostupnost mreže
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    //Provjeri jesu li Google usluge omogućene
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, Constants.PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "Usluga nije podržana za ovaj uređaj.");
                finish();
            }
            return false;
        }
        return true;
    }

    /***** METODE ZA MAPU *****/

    private void setUpMap(LatLng latLng) {
        int height = 309;
        int width = 242;
        Bitmap marker = BitmapFactory.decodeResource(getResources(), R.drawable.pin_me);
        marker = Bitmap.createScaledBitmap(marker, width, height, false);

        MarkerOptions myLocationMarker = new MarkerOptions()
                .position(latLng)
                .title("Ovdje sam!")
                .snippet("Spremni za književnu potragu? :) ")
                .icon(BitmapDescriptorFactory.fromBitmap(marker));
        mGoogleMap.addMarker(myLocationMarker);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
    }

    private void setPlaceMarker(LatLng location, NearbyPlace place) {
        int height = 280;
        int width = 220;
        int height2 = 205;
        int width2 = 161;
        //Knjižnica marker
        Bitmap library = BitmapFactory.decodeResource(getResources(), R.drawable.pin_library);
        library = Bitmap.createScaledBitmap(library, width, height, false);
        //Knjižara marker
        Bitmap bookstore = BitmapFactory.decodeResource(getResources(), R.drawable.pin_bookstore);
        bookstore = Bitmap.createScaledBitmap(bookstore, width, height, false);
        //Tisak marker
        Bitmap kiosk = BitmapFactory.decodeResource(getResources(), R.drawable.pin_kiosk);
        kiosk = Bitmap.createScaledBitmap(kiosk, width2, height2, false);

        String title = place.getType();
        if (place.getPlaceName().equals("Tisak")) {
            title = "Kiosk";
        }

        MarkerOptions placeMarker = new MarkerOptions()
                .position(location)
                .title(title + ": " + place.getPlaceName())
                .snippet(place.getPlaceVicinity());
        if(place.getType().equals("Knjižara") && !place.getPlaceName().equals("Tisak")) {
            placeMarker.icon(BitmapDescriptorFactory.fromBitmap(bookstore));
        } else if (place.getType().equals("Knjižnica")) {
            placeMarker.icon(BitmapDescriptorFactory.fromBitmap(library));
        } else if (place.getPlaceName().equals("Tisak")) {
            placeMarker.icon(BitmapDescriptorFactory.fromBitmap(kiosk));
        }
        mGoogleMap.addMarker(placeMarker);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        UiSettings uiSettings = this.mGoogleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //PERMISSION PROMJENE ZA ANDROID MARSHMELLOW UREĐAJE
            ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            return;
        }
        // Prikaz lokacije koju je dohvatio GoogleMaps sam bez dodatnog koda
        this.mGoogleMap.setMyLocationEnabled(true);
    }



}

