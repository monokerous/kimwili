package com.example.jeannebertoux.kimwili.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.jeannebertoux.kimwili.Activite;
import com.example.jeannebertoux.kimwili.ActiviteException;
import com.example.jeannebertoux.kimwili.Dal;
import com.example.jeannebertoux.kimwili.NiveauDouleur;
import com.example.jeannebertoux.kimwili.NiveauDouleurException;
import com.example.jeannebertoux.kimwili.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 * le type Niveau douleur activity.
 */
public class NiveauDouleurActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {

    private static final String TAG = "NiveauDouleurActivity";
    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau_douleur);

        chart = (LineChart) findViewById(R.id.linechart);

        chart.setOnChartGestureListener(NiveauDouleurActivity.this);
        chart.setOnChartValueSelectedListener(NiveauDouleurActivity.this);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);

        // fait apparaitre une limite
        LimitLine upper_limit = new LimitLine(8f, "Danger");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f,10f,0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);

        LimitLine lower_limit = new LimitLine(0f, "Trop bas");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f,10f,0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(15f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaximum(10f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f,10f,0);
        leftAxis.setDrawLimitLinesBehindData(true);

        //pour faire disparaitre la bare du graphe à droite
        chart.getAxisRight().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();
        //valeur instancié au pif pour tester une courbe a plusieur points.
        yValues.add(new Entry(0,10f));
        yValues.add(new Entry(1,9f));
        yValues.add(new Entry(2,7f));
        yValues.add(new Entry(3,3f));
        yValues.add(new Entry(4,5f));
        yValues.add(new Entry(5,6f));
        yValues.add(new Entry(6,8f));
        yValues.add(new Entry(7,9f));
        yValues.add(new Entry(8,4f));
        yValues.add(new Entry(9,2f));

        //nom de la courbe
        LineDataSet niveauDouleur = new LineDataSet(yValues, "Niveau de douleur");
        //défini la taille du graphe
        niveauDouleur.setFillAlpha(110);
        //défini la couleur de la courbe
        niveauDouleur.setColor(Color.RED);
        // défini l'épaisseur du trait de la courbe
        niveauDouleur.setLineWidth(3f);
        //défini la taille des valeur sur les points du graphe
        niveauDouleur.setValueTextSize(10f);
        //défini la couleur des valeurs sur les points du graphe
        niveauDouleur.setValueTextColor(Color.GREEN);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(niveauDouleur);

        LineData data = new LineData(dataSets);

        chart.setData(data);

        String[] values = new String[]{"Janvier", "Février","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Otobre","Novembre","Décembre"};

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValuesFormatter(values));
        xAxis.setGranularity(1);
        //pour faire apparaitre en bas du graphique les mois de l'année
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        // Initialisation instance bdd
        final Dal dal = new Dal(this);
        dal.open();

        Button btnAjoutDouleur = findViewById(R.id.btnAjoutDouleur);


        btnAjoutDouleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dal.close();
                Intent intent = new Intent(NiveauDouleurActivity.this, AjouterNiveauDouleur.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "OnChartGestureStart : X: " + me.getX() + "Y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "OnChartGestureEnd: " + lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG, "onChartLongPressed: ");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG, "onChartDoubleTapped: ");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG,"onChartSingleTapped: ");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG, "OnChartFling: veloX: " + velocityX + " veloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG, "onChartScale: ScaleX: " + scaleX + " ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG, "onChartTranslate: dX: " + dX + " dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG, "onChartTranslate: " + e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG, "onNothingSelected: ");
    }

    /**
     * The type My x axis values formatter.
     */
    public class MyXAxisValuesFormatter implements IAxisValueFormatter{
        private  String[] valeurs;

        /**
         * Instantiates a new My x axis values formatter.
         *
         * @param values the values
         */
        public MyXAxisValuesFormatter(String[] values){
            this.valeurs = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return valeurs[(int)value];
        }
    }
}
