package com.shojolahmed.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPlayAgain;
    private TextView sp1, sp2, Hint;

    private int clickCount = 0;
    private int scoreP1 = 0;
    private int scoreP2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.buttonView1);
        btn2 = findViewById(R.id.buttonView2);
        btn3 = findViewById(R.id.buttonView3);
        btn4 = findViewById(R.id.buttonView4);
        btn5 = findViewById(R.id.buttonView5);
        btn6 = findViewById(R.id.buttonView6);
        btn7 = findViewById(R.id.buttonView7);
        btn8 = findViewById(R.id.buttonView8);
        btn9 = findViewById(R.id.buttonView9);

        btnPlayAgain = findViewById(R.id.btnPlayAgainView);

        sp1 = findViewById(R.id.scoreView1);
        sp2 = findViewById(R.id.scoreView2);

        Hint = findViewById(R.id.txtHint);
    }

    private void playerTurn(Button btn)
    {
        if (clickCount % 2 == 0 && btn.getText() == "")
        {
            btn.setText("O");
            btn.setEnabled(false);
            clickCount++;
            playerTurnHint(2);
        }
        else if (clickCount % 2 == 1 && btn.getText() == "")
        {
            btn.setText("X");
            btn.setEnabled(false);
            clickCount++;
            playerTurnHint(1);
        }
    }

    private void playerWin(String sym, int player)
    {
        boolean won = false;

        // Horizontal
        if(btn1.getText() == sym && btn2.getText() == sym && btn3.getText() == sym) won = true;
        if(btn4.getText() == sym && btn5.getText() == sym && btn6.getText() == sym) won = true;
        if(btn7.getText() == sym && btn8.getText() == sym && btn9.getText() == sym) won = true;

        // Vertical
        if(btn1.getText() == sym && btn4.getText() == sym && btn7.getText() == sym) won = true;
        if(btn2.getText() == sym && btn5.getText() == sym && btn8.getText() == sym) won = true;
        if(btn3.getText() == sym && btn6.getText() == sym && btn9.getText() == sym) won = true;

        // Diagonal
        if(btn1.getText() == sym && btn5.getText() == sym && btn9.getText() == sym) won = true;
        if(btn3.getText() == sym && btn5.getText() == sym && btn7.getText() == sym) won = true;

        if(won)
        {
            btnEnabled(false);

            if (player == 1)
            {
                scoreP1++;
                clickCount = 1;
            }
            else if (player == 2)
            {
                scoreP2++;
                clickCount = 0;
            }
            updateScore();

            btnPlayAgain.setVisibility(View.VISIBLE);
            Hint.setText("Player " + player +" Wins!");
        }
    }

    private void playerDraw()
    {
        if (!btn1.isEnabled() && !btn2.isEnabled() && !btn3.isEnabled()
        && !btn4.isEnabled() && !btn5.isEnabled() && !btn6.isEnabled()
        && !btn7.isEnabled() && !btn8.isEnabled() && !btn9.isEnabled())
        {
            btnPlayAgain.setVisibility(View.VISIBLE);
            Hint.setText("Game Drawn!");
        }
    }

    private void updateScore()
    {
        sp1.setText(Integer.toString(scoreP1));
        sp2.setText(Integer.toString(scoreP2));
    }

    private void playerTurnHint(int player)
    {
        Hint.setText("Turn for Player " + player);
    }

    private void btnEnabled(boolean value)
    {
        btn1.setEnabled(value);
        btn2.setEnabled(value);
        btn3.setEnabled(value);
        btn4.setEnabled(value);
        btn5.setEnabled(value);
        btn6.setEnabled(value);
        btn7.setEnabled(value);
        btn8.setEnabled(value);
        btn9.setEnabled(value);
    }

    public void btnOnClick(View v)
    {
        playerTurn((Button) v);
        playerDraw();
        playerWin("O", 1);
        playerWin("X", 2);
    }

    public void btnResetOnClick(View v)
    {
        btnEnabled(true);

        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        clickCount = 0;
        scoreP1 = 0;
        scoreP2 = 0;
        updateScore();

        btnPlayAgain.setVisibility(View.GONE);
        playerTurnHint(1);
    }

    public void btnPlayAgainOnClick(View v)
    {
        btnEnabled(true);

        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        btnPlayAgain.setVisibility(View.GONE);

        if (clickCount % 2 == 0) playerTurnHint(1);
        else playerTurnHint(2);
    }
}