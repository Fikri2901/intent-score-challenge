package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MatchActivity extends AppCompatActivity {

    private TextView homeText, awayText, homeScoreText, awayScoreText;
    private ImageView homeImage, awayImage;
    private Bitmap homeBitmap, awayBitmap;
    private Uri homeUri, awayUri;
    private int homeScore, awayScore, request;
    private String homeScorerName = "", awayScorerName ="", scorerName;
    private String homeTeamName, awayTeamName ,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeScoreText = findViewById(R.id.score_home);
        awayScoreText = findViewById(R.id.score_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Data data = bundle.getParcelable("DATA_KEY");
            homeTeamName = data.getHomeText();
            awayTeamName = data.getAwayText();
            homeText.setText(homeTeamName);
            awayText.setText(awayTeamName);
            homeUri = data.getHomeUri();
            awayUri = data.getAwayUri();
            try {
                homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeImage.setImageBitmap(homeBitmap);
            awayImage.setImageBitmap(awayBitmap);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }



    public void tampilScoreA(View view){
        homeScore += 1;
        request = 1;
        homeScoreText.setText(Integer.toString(homeScore));
        Intent intent = new Intent(this, ScorerActivity.class);
        intent.putExtra("REQUEST_KEY", request);
        startActivityForResult(intent, 1);
    }

    public void tampilScoreB(View view){
        awayScore += 1;
        request = 2;
        awayScoreText.setText(Integer.toString(awayScore));
        Intent intent = new Intent(this, ScorerActivity.class);
        intent.putExtra("REQUEST_KEY", request);
        startActivityForResult(intent, 2);
    }

    public void cek(View view) {
        if (homeScore > awayScore){
            message = homeTeamName + " WIN";
            scorerName = homeScorerName;
        }else if(homeScore < awayScore){
            message = awayTeamName + " WIN";
            scorerName = awayScorerName;
        }else if(homeScore == awayScore){
            message = "DRAW";
            scorerName = "";
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("MESSAGE_KEY", message);
        intent.putExtra("SCORER_KEY", scorerName);
        startActivity(intent);

    }
}
