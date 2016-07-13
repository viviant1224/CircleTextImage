package circletextimage.viviant.com.circletextimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import circletextimage.viviant.com.circletextimage.view.CircleTextImage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1 = (Button)findViewById(R.id.def_bgcolor);
        Button bt2 = (Button)findViewById(R.id.change_size);
        Button bt3 = (Button)findViewById(R.id.random_bg_color);
        Button bt4 = (Button)findViewById(R.id.sub_first_character);
        Button bt5 = (Button)findViewById(R.id.def_text_color);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.def_bgcolor:
                Intent intent1 = new Intent(this, DefBGColorActivity.class);
                startActivity(intent1);
                break;
            case R.id.change_size:
                Intent intent2 = new Intent(this, ChangeSizeActivity.class);
                startActivity(intent2);
                break;
            case R.id.random_bg_color:
                Intent intent3 = new Intent(this, RandomBGColorActivity.class);
                startActivity(intent3);
                break;
            case R.id.sub_first_character:
                Intent intent4 = new Intent(this, SubFirstCharacterActivity.class);
                startActivity(intent4);
                break;
            case R.id.def_text_color:
                Intent intent5 = new Intent(this, DefTextColorActivity.class);
                startActivity(intent5);
                break;

        }
    }
}
