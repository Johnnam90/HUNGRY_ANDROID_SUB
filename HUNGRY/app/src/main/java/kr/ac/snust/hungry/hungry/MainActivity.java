package kr.ac.snust.hungry.hungry;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;





public class MainActivity extends Activity {
    //Main Activity
    ListView main_listView;
    Main_menuAdapter main_menuAdapter;
    ImageView main_thumb;
//    BackgroundTask task;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Profile picture
        main_thumb = (ImageView) findViewById(R.id.main_thumb);
        String imgUrl = "http://54.64.160.105:8080/img/thumb/Screen%20Shot%202015-06-09%20at%202.05.50%20PM.png";
        Glide.with(this).load(imgUrl).override(300, 300).centerCrop().into(main_thumb);
//        task = new BackgroundTask();
//        task.execute(imgUrl);
        //end


        //메인메뉴
        main_listView = (ListView)findViewById(R.id.main_listView);
        main_menuAdapter = new Main_menuAdapter(this);

        Resources res = getResources();
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_waiting), "번호표 관련"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_recent), "최근 게시물"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_popular), "인기 게시물"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_recommend), "음식점 추천"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_favorite), "관심 음식점"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_mine), "내 활동내역"));

        main_listView.setAdapter(main_menuAdapter);


        //메뉴 클릭 시 인텐트 전달
        main_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Main_menuItem curItem = (Main_menuItem) main_menuAdapter.getItem(position);
                String curData = curItem.getData();

                Intent intent = new Intent(getApplicationContext(), MiddleListActivity.class);
                intent.putExtra("menu", curData);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                //Toast.makeText(getApplicationContext(), "Selected : " + curData, Toast.LENGTH_LONG).show();
            }

        });
        //인텐트 END
        //메인메뉴 END



//        ImageRound imageRound = new ImageRound();
//        imageRound.getRoundedCornerBitmap();
    }


    /**
     * ImageView(main_thumb)에 URL을 이용하여 이미지를 나타내기 위한 클래스
     * .execute() 부분으로 실행
     * 첫 인자는 파마메터, 두 번째는 무상관, 세 번째는 반환값
     */
//    class BackgroundTask extends AsyncTask<String, Integer, Bitmap> {
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            try{
//                URL myFileUrl = new URL(urls[0]);
//                HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
//                conn.setDoInput(true);
//                conn.connect();
//
//                InputStream is = conn.getInputStream();
//
//                bitmap = BitmapFactory.decodeStream(is);
//
//
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//
//        protected void onPostExecute(Bitmap img){
//            main_thumb.setImageBitmap(bitmap);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
