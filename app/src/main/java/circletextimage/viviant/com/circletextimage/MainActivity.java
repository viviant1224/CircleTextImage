package circletextimage.viviant.com.circletextimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import circletextimage.viviant.com.circletextimage.view.CircleTextImage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleTextImage cti1 = (CircleTextImage) findViewById(R.id.cti1);
        cti1.setText4CircleImage("Java");

        CircleTextImage cti2 = (CircleTextImage) findViewById(R.id.cti2);
        cti2.setText4CircleImage("JavaScript");

        CircleTextImage cti3 = (CircleTextImage) findViewById(R.id.cti3);
        cti3.setText4CircleImage("好好学习，天天");

        CircleTextImage cti4 = (CircleTextImage) findViewById(R.id.cti4);
        cti4.setText4CircleImage("游戏");

        CircleTextImage cti5 = (CircleTextImage) findViewById(R.id.cti5);
        cti5.setText4CircleImage("python");

        CircleTextImage cti6 = (CircleTextImage) findViewById(R.id.cti6);
        cti6.setText4CircleImage("Object-C");




    }
}
