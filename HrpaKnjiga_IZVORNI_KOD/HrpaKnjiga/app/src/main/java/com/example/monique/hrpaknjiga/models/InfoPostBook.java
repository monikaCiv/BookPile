package com.example.monique.hrpaknjiga.models;

import com.example.monique.hrpaknjiga.R;

import java.util.ArrayList;

public class InfoPostBook {

    private  ArrayList<InfoPost> mInfo = new ArrayList<InfoPost>();

    public InfoPostBook() {
        mInfo.add(new InfoPost("Facebook",
                "Naša originalna Facebook stranica iz koje je proizašla ideja za ovu aplikaciju. Bacite pogled.",
                "https://www.facebook.com/HrpaKnjiga",
                R.drawable.info_facebook));

        mInfo.add(new InfoPost("Instagram",
                "Hrpa Knjiga uz facebook stranicu ima i skromnu instagram stranicu " +
                        "na kojoj još treba poraditi, ali svakako ste dobrodošli " +
                        "zapratiti nas već sada.",
                "https://www.instagram.com/hrpa.knjiga/",
                R.drawable.info_instagram));

        mInfo.add(new InfoPost("Sajam knjiga Interliber",
                "Najveći hrvatski sajam knjiga koji se održava svake godine u studenome" +
                        "na Zagrebačkom velesajmu.",
                "http://www.zv.hr/?id=506",
                R.drawable.info_interliber));

        mInfo.add(new InfoPost("Beogradski sajam knjiga",
                "Najveći sajam knjig u regiji s gotovo tisuću izlagača svake godine i " +
                        "šezdesetogodišnjom tradicijom.",
                "http://sajam.co.rs/active/sr-latin/home/details/_params/sajam_id/74364.html",
                R.drawable.info_beograd));

        mInfo.add(new InfoPost("Hails Hearts Nyc",
                "Booktube je zajednica youtubera koji svoja mišljenja o knjigama, kupovinu knjiga " +
                        "i sve ostalo vezano uz knjige žele podijeliti s drugima. Hailey je jedna od njih.",
                "https://www.youtube.com/channel/UCGeNhLPJHs8kJ9VfM35Okqg",
                R.drawable.info_hailey));

        mInfo.add(new InfoPost("PeruseProject",
                "Još jedna booktuberica koja mnogo čita i redovito objavljuje nove " +
                        "stvari na svom kanalu. Isplati se pogledati, pogotovo kada ne " +
                        "možete odlučiti koju knjigu sljedeću pročitati .",
                "https://www.youtube.com/user/PeruseProject",
                R.drawable.info_peruse));
    }

    public ArrayList<InfoPost> getInfo() {
        return mInfo;
    }
}
