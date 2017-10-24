package com.example.monique.hrpaknjiga.models;

import java.util.ArrayList;
import java.util.Random;

public class QuotesBook {
    private static ArrayList<Quote> mQuotes = new ArrayList<Quote>();

    //Konstruktor klase QuotesBook
    public QuotesBook() {
        //Korištena Array lista jer je dinamična pa je moguće dodavati nove citate bez mnogo izmjena u kodu.
        //0
        mQuotes.add(new Quote
                ("", "Ernest Hemingway",
                        "Nema odanijeg prijatelja od knjige."));
        //1
        mQuotes.add(new Quote
                ("Sumrak saga", "Stephenie Meyer",
                "Ljubav koju nekome podariš daje toj osobi moć da te slomi."));
        //2
        mQuotes.add(new Quote
               ("Patnje mladog Werthera", "Johann Wolfgang Goethe",
               "Jesam li to još uvijek ja, onaj isti " +
               "koji sam nekada lebdio u punom izobilju osjećaja, " +
               "a raj me je pratio na svakom koraku, i koji sam imao srce, " +
               "da bi njime u ljubavi obujmio čitav svijet?"));
        //3
        mQuotes.add(new Quote
                ("Gavran", "Edgar Allan Poe", "Nikad više."));
        //4
        mQuotes.add(new Quote
                ("Proljeću", "William Blake",
                "Prstima lijepim odjeni je, grud joj\n" +
                "Poljubi nježnim cjelovom, i stavi\n" +
                "Zlatnu joj krunu na klonulu glavu\n" +
                "Što uvojke je čedne za te splela!"));
        //5
        mQuotes.add(new Quote
                ("Lovac u žitu", "J. D. Salinger",
                "Znam da je to ludo, ali je to jedino što bih zaista želio biti."));
        //6
        mQuotes.add(new Quote
                ("Opomena", "Antun Branko Šimić",
                "Čovječe, pazi da ne ideš malen ispod zvijezda!"));
        //7
        mQuotes.add(new Quote
                ("Epilog", "Ivo Andrić",
                "– Hoćeš da usniš, sine moj?\n" +
                "\n" +
                "-Ne, oče, idem, idem da živim."));
        //8
        mQuotes.add(new Quote
                ("Pobratimstvo lica u svemiru", "Tin Ujević",
                "Ne boj se! Nisi sam! (...)Mi smo svi prešli iste putove u mraku, " +
                "mi smo svi jednako lutali u znaku traženja, i svima jednako se dive."));
        //9
        mQuotes.add(new Quote
                ("Tvrđava koja se ne predaje.", "Jure Kaštelan",
                "Ja sam tvrđava sa jedinom zastavom srca."));
        //10
        mQuotes.add(new Quote
                ("Majstore ugasi svijeću", "Slavko Mihalić",
                "Samo klauni znaju kako se možeš izvući: plaču kad im se smije i smiju se kad im plač razara lice."));
        //11
        mQuotes.add(new Quote
                ("U iščekivanju Godota", "Samuel Beckett",
                "Ja sam takav. Ili zaboravljam odmah ili ne zaboravljam nikad."));
        //12
        mQuotes.add(new Quote
                ("Približavanje oluje","Slavko Mihalić",
                "Bojim se za tebe, za mene je svejedno."));
        //13
        mQuotes.add(new Quote
                ("Na Drini ćuprija", "Ivo Andrić",
                "Život je neshvatljivo čudo, jer se neprestano troši i osipa, a ipak traje i stoji čvrsto \"kao na Drini ćuprija.\""));
        //14
        mQuotes.add(new Quote
                ("Starac i more", "Erenst Hemingway",
                "Ali čovjek nije stvoren za poraze. Čovjek može biti uništen, ali ne i pobijeđen."));
        //15
        mQuotes.add(new Quote
                ("Galeb Jonnathan Livingstone", "Richard Bach",
                "Kad bi naše prijateljstvo ovisilo o vremenu i prostoru..." +
                "Svladamo li prostor, ostaje nam samo OVDJE. " +
                "Svladamo li vrijeme, ostaje nam samo SADA. " +
                "Zar ne misliš da ćemo se na tom putu, " +
                "između Sada i Ovdje, ipak povremeno susretati?"));
        //16
        mQuotes.add(new Quote
                ("Dječak u prugastoj pidžami", "John Boyne",
                "Što je zapravo razlika? Pitao se. I tko je odlučio koji će ljudi nositi prugaste pidžame, a koji uniforme?"));
        //17
        mQuotes.add(new Quote
                ("Ana Karenjina", "Lav Nikolajevič Tolstoj",
                "Shvatio je da mu je ona ne samo bliska, već da sada ne zna gdje prestaje ona, a počinje on."));
        //18
        mQuotes.add(new Quote
                ("Ana Karenjina","Lav Nikolajevič Tolstoj",
                "Sve sretne obitelji su iste. Svaka nesretna obitelj nesretna je na svoj način."));
        //19
        mQuotes.add(new Quote
                ("Pjesma mrtvog pjesnika", "Dobriša Cesarić",
                "Jer knjiga ta, što držiš je u ruci, samo je dio mene koji spava. " +
                "I ko je čita – u život me budi. Probudi me, i bit ću tvoja java."));
        //20
        mQuotes.add(new Quote
                ("Mali princ", "Antoine de Saint Exupery",
                "Moram podnijeti dvije, tri gusjenice ako želim upoznati leptira."));
        //21
        mQuotes.add(new Quote
                ("Opatija Northanger", "Jane Austen",
                "Osoba, bilo muškarac ili žena, koja ne nalazi užitak u dobroj knjizi, mora biti nevjerojatno glupa."));
        //22
        mQuotes.add(new Quote
                ("Zovem se crvena", "Orhan Pamuk",
                "Nije to šala: u susretu sa Smrću ljudi – a posebno većina muškaraca hrabrih poput lavova – izgube mogućnost upravljanja vlastitim tijelom."));
        //23
        mQuotes.add(new Quote
                ("Do posljednjeg daha", "Anne Sward",
                "Izgubiti sebe je najopasnije, pokušavala me podučiti kad sam bila mlađa. Izgubiš li nešto drugo, to možeš uvijek nanovo osvojiti – ali ako izgubiš sebe, tko će te ići tražiti?"));
        //24
        mQuotes.add(new Quote
                ("Pijev život", "Yann Martel",
                "Strah je jedini istinski protivnik života. Jedino strah može pobijediti život."));
        //25
        mQuotes.add(new Quote
                ("Sto godina samoće","Gabriel Garcia Marquez",
                "Jedna minuta pomirenja vrijedi više nego cijeli život prijateljstva."));
        //26
        mQuotes.add(new Quote
                ("Harry Potter","J. K. Rowling",
                "Mnogim ljudima je puno lakše drugima oprostiti to što su bili u krivu, nego to što su bili u pravu."));
        //27
        mQuotes.add(new Quote
                ("Greška u našim zvijezdama","John Green",
                "Svijet nije tvornica koja ispunjava želje."));
        //28
        mQuotes.add(new Quote
                ("Greška u našim zvijezdama","John Green",
                "To je to kad je riječ o boli. Zahtjeva da ju osjetiš."));
        //29
        mQuotes.add(new Quote
                ("Greška u našim zvijezdama", "John Green",
                "Zaljubila sam se kako toneš u san. Najprije lagano, a zatim iznenada."));
        //30
        mQuotes.add(new Quote
                ("Mali princ", "Antoine de Saint Exupery",
                "Mnogo je teže suditi sebi samome nego drugima. Ako uspiješ sebi dobro suditi, znači da si pravi mudrac."));
        //31
        mQuotes.add(new Quote
                ("Igre gladi","Suzanne Collins",
                "Sretna igre gladi! Neka šanse uvijek budu u vašu korist."));
        //32
        mQuotes.add(new Quote
                ("Igre gladi","Suzanne Collins",
                "Evo ti savjeta. Ostani živa!"));
        //33
        mQuotes.add(new Quote
                ("Igre gladi","Suzanne Collins",
                "Da bi došlo do izdaje, najprije mora postojati povjerenje."));
        //34
        mQuotes.add(new Quote
                ("Harry Potter","J. K. Rowling",
                "Sreću je moguće pronaći i u najmračnijim vremenima, ukoliko se sjetimo upaliti svjetlo."));
        //35
        mQuotes.add(new Quote
                ("Harry Potter","J. K. Rowling",
                "Tvoje neuspješno shvaćanje kako postoje stvari strašnije od smrti" +
                "oduvijek je bilo tvoja najveća slabost."));
        //36
        mQuotes.add(new Quote
                ("Put oko svijeta u 80 dana", "Jules Verne",
                "Znanost je, čovječe, sazdana od pogrešaka, ali to su korisne pogreške " +
                "jer su malo pomalo dovele do istine."));
        //37
        mQuotes.add(new Quote
                ("Frankenstein", "Mary Shelley",
                "Čuvaj me se jer sam neustrašiv i to me čini moćnim."));
        //38
        mQuotes.add(new Quote
                ("Alisa u zemlji čuda", "Lewis Carroll",
                "Koliko dugo traje zauvijek? Ponekad, svega jednu sekundu."));
        //39
        mQuotes.add(new Quote
                ("Veliki Gatsby", "F. Scott Fitzgerald",
                "Volim je. To je početak i kraj svega."));
        //40
        mQuotes.add(new Quote
                ("Alisa u zemlji čuda", "Lewis Carroll",
                "Ako ne znaš kamo ideš, svaka cesta je pravi put."));
        //41
        mQuotes.add(new Quote
                ("Alisa u zemlji čuda", "Lewis Carroll",
                "Oni koji ne vjeruju u magiju, nikada ju neće pronaći."));
        //42
        mQuotes.add(new Quote
                ("Harry Potter","J. K. Rowling",
                "Dobro organiziranom umu, smrt je samo još jedna avantura."));
        //43
        mQuotes.add(new Quote
                ("Harry Potter","J. K. Rowling",
                "U snovima ulazimo u svijet koji u potpunosti pripada samo nama."));
        //44
        mQuotes.add(new Quote
                ("Harry Potter","J. K. Rowling",
                "Naši izbori pokazuju tko smo zapravo. Mnogo više nego što to pokazuju naše mogućnosti."));

        //45
        mQuotes.add(new Quote
                ("Nestala","Gillian Flynn",
                "Ljubav te tjera da budeš bolji čovjek. Zaista - zaista. Ali možda ti ljubav " +
                "prava ljubav daje dozvolu da budeš čovjek kakav zapravo jesi."));
        //46
        mQuotes.add(new Quote
                ("Princezini dnevnici","Meg Cabot",
                "Nikad ne poklanjam knjige. Knjige su osobna stvar i bilo be grozno " +
                "nekome pokloniti knjigu koja im se ne sviđa ili ju ne žele. Osim toga," +
                "srce bi mi bilo slomljeno ako ju ne pročitaju."));
        //47
        mQuotes.add(new Quote
                ("Grička vještica","Marija Jurić Zagorka",
                "Ima vjerne vječne ljubavi, no samo za odabrane."));
        //48
        mQuotes.add(new Quote
                ("Grička vještica","Marija Jurić Zagorka",
                "Nije li čudno što se ljudi tako rado bore za svoju vjeru, " +
                "a tako nerado žive po njenim zakonima."));
        //49
        mQuotes.add(new Quote
                ("Grička vještica","Marija Jurić Zagorka",
                "Zlo uvijek proždire samo sebe. Potrebno je samo strpljenja da se dočeka taj tren."));
        //50
        mQuotes.add(new Quote
                ("Grička vještica","Marija Jurić Zagorka",
                "Dobro srce katkad je čovjeku i narodu jedini neprijatelj."));
        //51
        mQuotes.add(new Quote
                ("Sumrak saga", "Stephenie Meyer",
                "Ako ti život pruži san što toliko nadvisuje sva tvoja očekivanja, nije razumno tugovati kad mu dođe kraj!"));
        //52
        mQuotes.add(new Quote
                ("U sebi", "Stephenie Meyer",
                "Kad bi mi netko dao izbor: zadržati tebe ili spasiti svijet? " +
                "Ne bih te se odrekao ni pod cijenu spašavanja milijardu života."));
        //53
        mQuotes.add(new Quote
                ("Alkemičar", "Paulo Coelho",
                "Ako si dovoljno hrabar reći doviđenja, " +
                "život će te nagraditi novim zdravo."));
        //54
        mQuotes.add(new Quote
                ("Alkemičar", "Paulo Coelho",
                "Najvažnije susrete duše dogovaraju mnogo prije nego što se tijela vidjela."));
        //55
        mQuotes.add(new Quote
                ("Alkemičar", "Paulo Coelho",
                "Jednog ćeš se dana probuditi i nećeš imati vremena učiniti stvari koje si oduvijek htio. " +
                "Učini ih sada!"));
        //56
        mQuotes.add(new Quote
                ("Gospodar prstenova", "J. R. R. Tolkien",
                "Nisu izgubljeni svi koji lutaju."));
        //57
        mQuotes.add(new Quote
                ("Gospodar prstenova", "J. R. R. Tolkien",
                "Kad bi više nas više čeznulo za hranom, radosti i pjesmom, nego za zlatom, svijet bi bio mnogo veseliji."));
        //58
        mQuotes.add(new Quote
                ("Gospodar prstenova", "J. R. R. Tolkien",
                "Čak i najmanja osoba može promijeniti tijek budućnosti."));
        //59
        mQuotes.add(new Quote
                ("Gospodar prstenova", "J. R. R. Tolkien",
                "Neke je stvari bolje započeti, nego zaobići, čak i kada bi njihov završetak mogao biti mračan."));
        //60
        mQuotes.add(new Quote
                ("Pjesma leda i vatre", "George R. R. Martin",
                "Čitatelj, prije nego što umre, živi tisuću života. Čovjek koji ne čita, živi samo jedan."));
        //61
        mQuotes.add(new Quote
                ("Ponos i predrasude", "Jane Austen",
                "Ženska je mašta veome brza. Ona u trenutku skače od sviđanja do ljubavi, od ljubavi do braka."));

        //62
        mQuotes.add(new Quote
                ("1984", "George Orwell",
                        "Ništa nije tvoje vlastito osim nekoliko kubičnih centimetara u tvojoj lubanji."));
        //63
        mQuotes.add(new Quote
                ("Puhač stakla s Murana", "Marina Fiorato",
                        "Imati dijete znači svom srcu dopustiti da se šeta izvan tijela."));
        //64
        mQuotes.add(new Quote
                ("Vlati trave", "Walt Withman",
                "Ne budite potišteni, ljubav će ipak riješiti probleme slobode. Oni koji ljube postat će nepobjedivi."));
        //65
        mQuotes.add(new Quote
                ("Alisa u zemlji čuda", "Lewis Carrol",
                "Ali ja neću među lude ljude, ogradila se Alice. Ah, tu nema pomoći,rekla je mačka. Svi smo mi ovdje ludi."));
        //66
        mQuotes.add(new Quote
                ("Silimarilion", "J. R. R. Tolkien",
                "Mnogo je neobičnih slučajnosti na svijetu-reče Mithrandir-a pomoć će često stići iz ruku slabih kad Mudri posrnu."));
        //67
        mQuotes.add(new Quote
                ("Praskozorje", "Stephenie Meyer",
                "Kako ti ja izgledam? Kao čarobnjak iz Oza? Treba ti mozak? Treba ti srce? Samo daj. Uzmi moje. Uzmi sve što imam."));
        //68
        mQuotes.add(new Quote
                ("Zločin i kazna", "F. M. Dostojevski",
                "Događa se da se sretnemo i s potpuno nepoznatim ljudima za koje se počnemo zanimatiod prvog pogleda, nekako naglo, neočekivano, prije nego što progovorimo s njima i jednu riječ."));
        //69
        mQuotes.add(new Quote
                ("Philobiblon", "Richard de Bury",
                "U knjigama se susrećem s mrtvima kao da su živi. U knjigama gledam stvari koje će tek doći. Sve stvari propadaju i prolaze s vremenom; sva bi slava pala u zaborav da Bog nije smrtnicima dao pomoć u vidu knjiga."));
        //70
        mQuotes.add(new Quote
                ("Srce od tinte", "Cornelia Funke",
                "Kao što je Mo već rekao: pisanje priča doista ima neke veze s čarolijom."));
        //71
        mQuotes.add(new Quote
                ("Harry Potter i kamen mudraca", "J. K. Rowling",
                "Eh, glazba, glazba, reče Dumbledore otirući oči. To je čarolija koja baca u zasjenak sve što mi ovdje radimo."));
        //72
        mQuotes.add(new Quote
                ("Gospodar prstenova", "J. R. R. Tolkien",
                "Noć se rugala jutru koje je izgubila, a studen je proklinjala toplinu za kojom je žudjela."));
        //73
        mQuotes.add(new Quote
                ("Strah mudroga", "Patrick Rothfus",
                "Gorko poput lijeka, okrutnije od ogledala, putovanje, kao veliki učitelj, sve izjednačuje. Dugo putovanje naučit će te više o tebi samom nego sto godina samoispitivanja u tišini."));
        //74
        mQuotes.add(new Quote
                ("Boja nara", "Sergej Parajanov",
                "Knjige moraju biti dobro čuvane i čitane, jer knjige su duša i život."));
        //75
        mQuotes.add(new Quote
                ("General u svom labirintu", "G. G. Marquez",
                "Nikad se više neću zaljubiti...kao da imaš dvije duše u isto vrijeme."));
        //76
        mQuotes.add(new Quote
                ("Sjena vjetra", "Carlos Ruiz Zafon",
                 "Onaj koji doista voli, voli u tišini: u djelima, a nikad riječima."));
        //77
        mQuotes.add(new Quote
                ("", "Mark Twain",
                "Život je kratak, prekrši pravila. Oprosti brzo, ljubi polako." +
                " Voli istinski. Smij se nekontrolirano i nikada nemoj žaliti ni za čime " +
                "što te nasmijavalo."));
        //78
        mQuotes.add(new Quote
                ("", "Albert Camus",
                "Tko prestane biti prijatelj, prijatelj nikada nije ni bio."));
        //79
        mQuotes.add(new Quote
                ("", "James Faulkner",
                "Ljudi kažu da postoje i druge ribe u moru. " +
                "Ja im na to odgovaram: *ebite se, ona je bila moje more."));
        //80
        mQuotes.add(new Quote
                ("Sjena vjetra", "Carlos Ruiz Zafon",
                        "Ako moraš stati da razmisliš voliš li nekoga, odavno ga više ne voliš."));
        //81
        mQuotes.add(new Quote
                ("Sjena vjetra", "Carlos Ruiz Zafon",
                "U životu nije važno samo imati dobre karte, već ih " +
                "dobro odigrati čak i kad su loše."));
        //82
        mQuotes.add(new Quote
                ("", "Ivo Andrić",
                "Što ne boli nije život. Što ne prolazi nije sreća."));
        //83
        mQuotes.add(new Quote
                ("", "William Shakespeare",
                "Stvari nisu ni dobre ni loše. Samo ih razmišljanje čini takvima."));
        //84
        mQuotes.add(new Quote
                ("", "Oscar Wilde",
                "Kad jednoj ljubavi dođe kraj, slabići kukaju, " +
                "energični smjesta pronađu novu ljubav, " +
                "a pametni su je već odavno imali u rezervi."));
        //85
        mQuotes.add(new Quote
                ("", "Edmund Wilson",
                "Ne postoje dvije osobe koje su pročitale istu knjigu"));

        //86
        mQuotes.add(new Quote
                ("", "Neil Gaiman",
                "Knjiga je san koji držite u rukama."));
        //87
        mQuotes.add(new Quote
                ("", "Josh Jameson",
                "I onda dođe vrijeme kada morate odabrati između okretanja još jedne " +
                "stranice ili zatvaranje knjige."));
        //88
        mQuotes.add(new Quote
                ("", "Woodrow Wilson",
                "Kada bi bilo moguće razgovarati s čovjekom koji je napisao knjigu, knjigu " +
                "ne bih uopće čitao."));
        //89
        mQuotes.add(new Quote
                ("", "P. J. O'Rourke",
                "Uvijek čitajte knjige koje će vas prikazati u dobrom svjetlu u slučaju " +
                "da umrete usred čitanja."));
        //90
        mQuotes.add(new Quote
                ("", "John Green",
                "Dobre knjige pomažu ti shvatiti i čine da se osjećaš shvaćenim."));
        //91
        mQuotes.add(new Quote
                ("", "Gilbert K. Chesterton",
                "Dobar roman govori istinu o svome junaku, a loš roman istinu u svome autoru."));
        //92
        mQuotes.add(new Quote
                ("", "George Bernard Shaw",
                 "Ne dajte djetetu knjigu koju sami ne biste pročitali." +
                         ""));
        //93
        mQuotes.add(new Quote
                ("", "Henry Ward Beecher",
                "Nema većeg dokaza slabosti ljudske prirode od knjigoljupca u knjižari."));
        //94
        mQuotes.add(new Quote
                ("", "William Lyon Phelps",
                "Čitatelje dijelim u dvije skupine: one koji čitaju da zaborave što su pričitali " +
                        "i one koji čitaju ne bi li zapamtili."));
        //95
        mQuotes.add(new Quote
                ("", "Joseph Brodsky",
                "Postoji veći zločin od spaljivanja knjiga. Ne čitati ih."));
        //96
        mQuotes.add(new Quote
                ("", "Paul Sweeney",
                "Kad okrenete posljednju stranicu i pomalo osjećate kao da ste " +
                        "upravo izgubili prijatelja, znak je da ste pročitali dobru knjigu."));
        //97
        mQuotes.add(new Quote
                ("", "R. D. Cumming",
                "Dobra knjiga nikad ne završava."));
        //98
        mQuotes.add(new Quote
                ("Wide Awake", "David Levithan",
                "Ne tražite sreću samo za sebe. Tražite je za svakoga. " +
                "Kroz dobrotu. Kroz milost."));
        //99
        mQuotes.add(new Quote
                ("Gonič zmajeva", "Khaled Hosseini",
                "Ljudi koji uvijek kažu ono što misle imaju problem jer vjeruju da i svi " +
                        "ostali tako čine."));
        //100
        mQuotes.add(new Quote
                ("Harry Potter i kamen mudraca", "J. K. Rowling",
                "Potrebno je mnogo hrabrosti ne bi li se suprostavili " +
                "svojim neprijateljima. Ipak, jednako mnogo hrabrosti  potrebno je za " +
                "suprotstaviti se svojim prijateljima."));

        //101
        mQuotes.add(new Quote
                ("Seks i grad", "Candace Bushnell",
                        "Muškarac je možda otkrio vatru, ali je žena otkrila kako se igrati s njom."));

        //102
        mQuotes.add(new Quote
                ("Posljednja pjesma", "Nicholas Sparks",
                        "Mislim, ako veza ne može preživjeti dugoročno, zašto bi onda zaboga bila vrijedna mog vremena i energije kratkoročno?"));

        //103
        mQuotes.add(new Quote
                ("Eleanor and Park", "Rainbow Rowell",
                        "Držati Eleanorinu ruku je bilo poput držanja leptira. Ili otkucaja srca. Poput držanja nečega cjelovitog, i potpuno živog."));

        //104
        mQuotes.add(new Quote
                ("Artemis Fowl", "Eoin Colfer",
                        "Smjelost je neznanje. Ako se osjećaš odvažno, to je zato što postoji nešto što ne znaš."));

        //105
        mQuotes.add(new Quote
                ("Grad od kostiju", "Cassandra Clare",
                        "Sve znanje boli."));

        //106
        mQuotes.add(new Quote
                ("Odana", "Veronica Roth",
                        "Znanje je moć. Moć da činiš zlo... ili moć da činiš dobro. Moć sama po sebi nije zla. Stoga ni znanje nije zlo."));

        //107
        mQuotes.add(new Quote
                ("Zameo ih vjetar", "Margaret Mitchell",
                        "Bilo je bolje znati ono najgore nego se pitati."));

        //108
        mQuotes.add(new Quote
                ("Izgubljeni simbol", "Dan Brown",
                        "Snaga ljudske misli eksponencijalno raste s brojem umova koji dijele tu misao."));

        //109
        mQuotes.add(new Quote
                ("Ime ruže", "Umberto Eco",
                        "Učenje se ne sastoji samo od spoznaje što moramo ili možemo učiniti, nego i od spoznaje što bismo mogli i što možda ne bismo smjeli učiniti.   "));

        //110
        mQuotes.add(new Quote
                ("Harry Potter i Kamen mudraca", "J. K. Rowling",
                        "Istina je nešto lijepo i strašno pa zato treba s njom oprezno postupati."));

        //111
        mQuotes.add(new Quote
                ("Zameo ih vjetar", "Margaret Mitchell",
                        "Za poreze i rođenje djece nikada nije pravo vrijeme."));

        //112
        mQuotes.add(new Quote
                ("Charlijev svijet", "Stephen Chbosky",
                        "Prihvaćamo onu ljubav koju mislimo da zaslužujemo."));

        //113
        mQuotes.add(new Quote
                ("Coraline", "Neil Gaiman",
                        "Bajke su i više nego istinite: ne zato što nam govore da zmajevi postoje, nego zato što nam govore da su zmajevi nepobjedivi"));

        //114
        mQuotes.add(new Quote
                ("Alisa u zemlji čuda", "Lewis Carroll",
                        "Nema smisla vraćati se na jučer, tada sam bila druga osoba"));

        //115
        mQuotes.add(new Quote
                ("Važno je zvati se Ernest", "Oscar Wilde",
                        "Istina je rijetko kada čista i nikada jednostavna"));

        //116
        mQuotes.add(new Quote
                ("Gotovi eseji 2", "Aldous Huxley",
                        "Činjenice ne prestaju postojati zato što ih se zanemaruje."));

        //117
        mQuotes.add(new Quote
                ("Alkemičar", "Paulo Coelho",
                        "Tajna života je, zapravo, pasti sedam puta i ustati osam puta."));

        //118
        mQuotes.add(new Quote
                ("Slika Doriana Greya", "Oscar Wilde",
                        "Knjige koje svijet zove nemoralnim jesu knjige koje svijetu pokazuju njegov sram."));

        //119
        mQuotes.add(new Quote
                ("Spašavajući Francescu", "Melina Marchetta",
                        "Istina te ne oslobađa, znaš. Ona čini da se osjećaš čudno i osramoćeno i bespomoćno i crveno u licu i prestrašeno i okamenjeno i ranjivo.'' "));

        //120
        mQuotes.add(new Quote
                ("Istočno od raja", "John Steinbeck",
                        "U istini je više ljepote, čak i ako je ta ljepota strašna."));

    }

    //Funkcija koja vraća nasumični element niza.
    public Quote getQuote() {
        Random quoteGenerator = new Random();
        Quote quote = new Quote("","","");
        int index = quoteGenerator.nextInt(mQuotes.size());
        quote = mQuotes.get(index);
        return  quote;
    }
}
