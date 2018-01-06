package org.androidtown.basiclayoutreview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   private  Button btngoal, btn1, btn2, btn3, btn4;
   private  ConstraintLayout stage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onclickListener();

    }

    private void init(){
        stage = (ConstraintLayout) findViewById(R.id.stage);
        btngoal = (Button) findViewById(R.id.btngoal);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
    }

    private void onclickListener(){ //클릭 리스너를 달아줌.
       stage.setOnClickListener(this); //자기를 클릭하면! onClick리스너에서 반응함.
        btngoal.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {// onclick리스너 메서드 인자로 받는다는 의미 임! view인자로!
        //클릭된 버튼을 사용하기 위해 시스템에서 넘겨받은 뷰를
        //원래의 버튼으로 캐스팅해준다.
        if (view instanceof Button) {
            //view 변수가 Button 클래스의 인스턴스 인지를 체크, 온클릭 리스너에서 전달받은 뷰가 버튼하고 같다면
            Button original = (Button) view;  // original이라는 변수에 버튼으로 형변환한 것을 담음.


            //실제 날아갈 더미를 생성해서 상위 레이아웃에 담은 후에 처리한다.
            final Button dummy = new Button(this);
            //생성된 더미에 클릭한 버튼의 속성값을 적용
            dummy.setText(original.getText().toString());
            dummy.setWidth(original.getWidth());
            dummy.setHeight(original.getHeight());
            dummy.setBackgroundColor(Color.RED);

            //부모 레이아웃을 가져와서 원래 클래스로 캐스팅
            LinearLayout parent = (LinearLayout) original.getParent();


            dummy.setX(original.getX() + parent.getX()); //dummy에 오리지널(리니어레이아웃)의 위치값과 상위 레이아웃의 위치값을을 가지고 온것을 셋한다.
            dummy.setY(original.getY() + parent.getY()); //

            //더미를 상위 레이아웃에 담는다.
            stage.addView(dummy);

            float goalY = btngoal.getY();
            float goalX = btngoal.getX();

            ObjectAnimator aniY = ObjectAnimator.ofFloat(dummy, "translationY", goalY);
            ObjectAnimator aniX = ObjectAnimator.ofFloat(dummy, "translationX", goalX);
            ObjectAnimator aniR = ObjectAnimator.ofFloat(dummy, "rotation", 720);

            AnimatorSet aniSet = new AnimatorSet();
            aniSet.playTogether(aniY,aniX, aniR);
            aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
            aniSet.setDuration(3000);
            aniSet.start();

            //애니메이션 종료를 체크하기 위한 리스너를 달아준다.
            aniSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    stage.removeView(dummy);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            aniSet.start();
        }
    }



}
//        stage = (LinearLayout)findViewById(R.id.stage);
//
//        // 버튼에 커스텀 레이아웃 적용
//
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
//
//        //버튼을 코드로 생성
//        Button player = new Button(this);
//        player.setWidth(100);
//        player.setHeight(100);
//        player.setText("P");
//        player.setY(50);
//
//        //빨간색 버튼을 Y축 250 위치에 생성
//        Button player2 = new Button(this); // 버튼을
//        player2.setBackgroundColor(Color.RED);
//        player2.setWidth(100);
//        player2.setHeight(100);
//        player2.setText("P");
//        player2.setY(0);
//
//
//        // 컨테이너에 뷰를 담는다.
//        stage.addView(player);
//        stage.addView(player2);
