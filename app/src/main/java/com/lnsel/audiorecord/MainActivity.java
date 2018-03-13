package com.lnsel.audiorecord;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import nl.changer.audiowife.AudioWife;

public class MainActivity extends AppCompatActivity {

    ListView list;
    CustomAdapter adapter;
    public  MainActivity CustomListView = null;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();


    TextView run_time;
    TextView total_time;
    Button cell_audio_play,cell_audio_pause;
    SeekBar seeker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seeker=findViewById(R.id.seeker);
        cell_audio_play=findViewById(R.id.cell_audio_play);
        cell_audio_pause=findViewById(R.id.cell_audio_pause);
        run_time = findViewById(R.id.run_time);
        total_time=findViewById(R.id.total_time);

        CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res =getResources();
        list= ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );

    }

    /****** Function to set data in ArrayList *************/
    public void setListData()
    {

        for (int i = 0; i < 11; i++) {

            final ListModel sched = new ListModel();

            /******* Firstly take data in model object ******/
            sched.setCompanyName("Company "+i);
            sched.setImage("image"+i);
            sched.setUrl("http:\\www."+i+".com");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }

    }


    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {
        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);


        // AudioWife.getInstance().release();
        File file = new File("/storage/emulated/0/Vhortext/17317557491520320225.3gp");
        AudioWife.getInstance()
                .init(MainActivity.this, Uri.fromFile(file))
                .setPlayView(cell_audio_play)
                .setPauseView(cell_audio_pause)
                .setSeekBar(seeker)
                .setRuntimeView(run_time)
                .setTotalTimeView(total_time);

        // mPlayerContainer = Parent view to add default player UI to.
        //LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //AudioWife.getInstance().init(mContext, Uri.fromFile(file)).useDefaultUi(ll_audio_container,inflater);

        AudioWife.getInstance().play();

        AudioWife.getInstance().addOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(CustomListView, "Completed", Toast.LENGTH_SHORT).show();
                // do you stuff.
            }
        });

        AudioWife.getInstance().addOnPlayClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(CustomListView, "Play", Toast.LENGTH_SHORT).show();
                // get-set-go. Lets dance.
            }
        });

        AudioWife.getInstance().addOnPauseClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(CustomListView, "Pause", Toast.LENGTH_SHORT).show();
                // Your on audio pause stuff.
            }
        });
    }
}
