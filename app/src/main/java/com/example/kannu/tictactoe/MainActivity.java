package com.example.kannu.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        int activePlayer = 0;
        int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        boolean gameIsActive = true;

        public void dropIn (View view)
        {
            ImageView counter = (ImageView) view;
            int tappedCounter = Integer.parseInt(counter.getTag().toString());
            if (gameState[tappedCounter] == 2 && gameIsActive) {
                gameState[tappedCounter] = activePlayer;
                tappedCounter = activePlayer;
                counter.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.cross);
                    activePlayer = 1;
                } else if (activePlayer == 1) {
                    counter.setImageResource(R.drawable.zero);
                    activePlayer = 0;
                }
                counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                            gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                            gameState[winningPosition[0]] != 2) {
                        gameIsActive = false;
                        String winner = "red";
                        if (gameState[winningPosition[0]] == 0) {
                            winner = "yellow";
                        }
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText(winner + " has won!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
                        layout.setVisibility(View.VISIBLE);


                    } else {

                        boolean gameIsOver = true;

                        for (int counterState : gameState) {

                            if (counterState == 2) gameIsOver = false;

                        }

                        if (gameIsOver) {

                            TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                            winnerMessage.setText("It's a draw");

                            LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

                            layout.setVisibility(View.VISIBLE);

                        }

                    }
                }

            }
        }
        public void playAgain (View view){


            Intent i = new Intent(this, MainActivity.class); //change it to your main class
            //the following 2 tags are for clearing the backStack and start fresh
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(i);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
