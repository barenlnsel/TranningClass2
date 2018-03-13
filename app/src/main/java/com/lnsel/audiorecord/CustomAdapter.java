package com.lnsel.audiorecord;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import nl.changer.audiowife.AudioWife;

/**
 * Created by db on 3/6/2018.
 */

public class CustomAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    ListModel tempValues=null;
    int i=0;

    /*************  CustomAdapter Constructor *****************/
    public CustomAdapter(Activity a, ArrayList d,Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data=d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView run_time;
        public TextView total_time;
        public TextView textWide;
        public ImageView image;
        public Button cell_audio_play,cell_audio_pause;
        public SeekBar seeker;

    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        final ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.tabitem, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.seeker=(SeekBar)vi.findViewById(R.id.seeker);
            holder.cell_audio_play=(Button)vi.findViewById(R.id.cell_audio_play);
            holder.cell_audio_pause=(Button)vi.findViewById(R.id.cell_audio_pause);
            holder.run_time = (TextView) vi.findViewById(R.id.run_time);
            holder.total_time=(TextView)vi.findViewById(R.id.total_time);
            holder.image=(ImageView)vi.findViewById(R.id.image);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
           // holder.text.setText("No Data");
        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = ( ListModel ) data.get( position );

            /************  Set Model values in Holder elements ***********/

           // holder.text.setText( tempValues.getCompanyName() );
           // holder.text1.setText( tempValues.getUrl() );
            holder.image.setImageResource(res.getIdentifier(
                            "com.androidexample.customlistview:drawable/"+tempValues.getImage()
                            ,null,null));

            /******** Set Item Click Listner for LayoutInflater for each row *******/

            holder.cell_audio_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // AudioWife.getInstance().release();
                    /*File file = new File("/storage/emulated/0/Vhortext/9653510561520319842.3gp");
                    AudioWife.getInstance()
                            .init(activity, Uri.fromFile(file))
                            .setPlayView(holder.cell_audio_play)
                            .setPauseView(holder.cell_audio_pause)
                            .setSeekBar(holder.seeker)
                            .setRuntimeView(holder.run_time)
                            .setTotalTimeView(holder.total_time);*/

                    // mPlayerContainer = Parent view to add default player UI to.
                    //LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    //AudioWife.getInstance().init(mContext, Uri.fromFile(file)).useDefaultUi(ll_audio_container,inflater);

                   /* AudioWife.getInstance().play();

                    AudioWife.getInstance().addOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Toast.makeText(activity, "Completed", Toast.LENGTH_SHORT).show();
                            // do you stuff.
                        }
                    });

                    AudioWife.getInstance().addOnPlayClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Toast.makeText(activity, "Play", Toast.LENGTH_SHORT).show();
                            // get-set-go. Lets dance.
                        }
                    });

                    AudioWife.getInstance().addOnPauseClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Toast.makeText(activity, "Pause", Toast.LENGTH_SHORT).show();
                            // Your on audio pause stuff.
                        }
                    });*/
                }
            });

            vi.setOnClickListener(new OnItemClickListener( position ));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {

            MainActivity sct = (MainActivity)activity;
            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
            sct.onItemClick(mPosition);
        }
    }
}