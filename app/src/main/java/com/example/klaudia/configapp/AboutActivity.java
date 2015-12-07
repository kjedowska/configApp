package com.example.klaudia.configapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private final static String ABOUT = "Aplikacja „Przyjazne Słowa” powstała w ramach wspólnego projektu „non-profit” Politechniki Gdańskiej i Fundacji- Instytut Wspomagania Rozwoju Dziecka w Gdańsku. W projekcie uczestniczyli studenci  z Wydziału Elektroniki, Telekomunikacji i Informatyki PG  pod kierunkiem dr inż. Agnieszki Landowskiej oraz specjaliści z  Instytutu Wspomagania Rozwoju Dziecka w Gdańsku, pod kierunkiem dr Anny Budzińskiej i Iwony Ruty-Sominki. \n" +
            "Aplikacja „Przyjazne Słowa” zaiplementowana przez studentów PG, powstała w oparciu o dokładne wskazówki merytoryczne dr Anny Budzińskiej i Iwony Ruty-Sominki z Instytutu Wspomagania Rozwoju Dziecka w Gdańsku.  Produktem projektu jest aplikacja na system Android, z uwzględnieniem konkretnej wersji używanej na urządzeniach posiadanych przez pracowników Instytutu Wspomagania Rozwoju Dziecka w Gdańsku. Użytkownik docelowy rozwija umiejętności rozumienia mowy poprzez przyjazne zadania dostosowane poziomem trudności do jego możliwości.\n" +
            " Aplikacja „Przyjazne Słowa” jest całkowicie bezpłatna.  Mamy nadzieję, że dzięki temu będzie w pełni dostępna zarówno dla nauczycieli, jak i rodziców. Aplikację można pobrać i stosować w domu, szkole, czy innej placówce edukacyjnej, w której przebywa dziecku. Mogą z niej korzystać dzieci, młodzież i osoby dorosłe. Aplikacja została opracowana z myślą o osobach z autyzmem, ale  mogą z niej korzystać również osoby z innymi zaburzeniami.   \n" +
            "\n" +
            "Ponieważ aplikacja jest projektem „open source” zapraszamy do udoskonalania tego narzędzia. Każdy użytkownik może pobrać kod źródłowy aplikacji i samodzielnie rozwijać jej funkcje czy dopasować ją do specyfiki stosowanej terapii.\n" +
            "Więcej informacji o projekcie: autyzm.eti.pg.gda.pl \n" +
            "Więcej informacji o Instytucie Wspomagania Rozwoju dziecka  i metodach  terapii:  www.iwrd.pl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView text = (TextView)findViewById(R.id.aboutTxtView);
        text.setText(ABOUT);
    }


}
