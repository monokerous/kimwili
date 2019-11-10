package com.example.jeannebertoux.kimwili.Controller;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.jeannebertoux.kimwili.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 * le type Statistique activity.
 */
public class StatistiqueActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {

    private LineChart courbeKmParcouru, courbeNiveauDouleur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_statistique);
        courbeKmParcouru = (LineChart) findViewById(R.id.linechart2);
        courbeNiveauDouleur = (LineChart) findViewById(R.id.linechart3);

        // ------ COURBE KM PARCOURUS --------
        courbeKmParcouru.setOnChartGestureListener(StatistiqueActivity.this);
        courbeKmParcouru.setOnChartValueSelectedListener(StatistiqueActivity.this);

        courbeKmParcouru.setDragEnabled(true);
        courbeKmParcouru.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();
        //valeur instancié au pif pour tester une courbe a plusieur points.
        yValues.add(new Entry(0,10f));
        yValues.add(new Entry(1,20f));
        yValues.add(new Entry(2,30f));
        yValues.add(new Entry(3,5f));
        yValues.add(new Entry(4,15f));
        yValues.add(new Entry(5,17f));
        yValues.add(new Entry(6,12f));
        yValues.add(new Entry(7,9f));
        yValues.add(new Entry(8,4f));
        yValues.add(new Entry(9,2f));

        //nom de la courbe
        LineDataSet kmParcouru = new LineDataSet(yValues, "Km parcourus");
        //défini la taille du graphe
        kmParcouru.setFillAlpha(70);
        //défini la couleur de la courbe
        kmParcouru.setColor(Color.BLUE);
        // défini l'épaisseur du trait de la courbe
        kmParcouru.setLineWidth(3f);
        //défini la taille des valeur sur les points du graphe
        kmParcouru.setValueTextSize(10f);
        //défini la couleur des valeurs sur les points du graphe
        kmParcouru.setValueTextColor(Color.CYAN);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(kmParcouru);

        LineData data = new LineData(dataSets);

        courbeKmParcouru.setData(data);

        // ------- COURBE NIVEAU DE DOULEUR --------

        courbeNiveauDouleur.setOnChartGestureListener(StatistiqueActivity.this);
        courbeNiveauDouleur.setOnChartValueSelectedListener(StatistiqueActivity.this);

        courbeNiveauDouleur.setDragEnabled(true);
        courbeNiveauDouleur.setScaleEnabled(false);

        ArrayList<Entry> valeurY = new ArrayList<>();
        //valeur instancié au pif pour tester une courbe a plusieur points.
        valeurY.add(new Entry(0,10f));
        valeurY.add(new Entry(1,20f));
        valeurY.add(new Entry(2,30f));
        valeurY.add(new Entry(3,5f));
        valeurY.add(new Entry(4,15f));
        valeurY.add(new Entry(5,17f));
        valeurY.add(new Entry(6,12f));
        valeurY.add(new Entry(7,9f));
        valeurY.add(new Entry(8,4f));
        valeurY.add(new Entry(9,2f));

        //nom de la courbe
        LineDataSet niveauDeDouleur = new LineDataSet(valeurY, "Niveau de douleur");
        //défini la taille du graphe
        niveauDeDouleur.setFillAlpha(70);
        //défini la couleur de la courbe
        niveauDeDouleur.setColor(Color.BLACK);
        // défini l'épaisseur du trait de la courbe
        niveauDeDouleur.setLineWidth(3f);
        //défini la taille des valeur sur les points du graphe
        niveauDeDouleur.setValueTextSize(10f);
        //défini la couleur des valeurs sur les points du graphe
        niveauDeDouleur.setValueTextColor(Color.RED);

        ArrayList<ILineDataSet> donnees = new ArrayList<>();
        donnees.add(niveauDeDouleur);

        LineData donne = new LineData(donnees);

        courbeKmParcouru.setData(donne);

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
