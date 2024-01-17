package com.route.xogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class XoGame extends AppCompatActivity {

    int counter = 0;

    TextView player1result;
    TextView player2result;

    ConstraintLayout constraintLayout;
    int player1score = 0, player2score = 0;
    String[] intial = new String[]{
            "", "", "",
            "", "", "",
            "", "", ""};
    ArrayList<String> board = new ArrayList<>(Arrays.asList(intial));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xo_game);
        player1result = findViewById(R.id.number_1);
        player2result = findViewById(R.id.number_2);
        constraintLayout = findViewById(R.id.mainconstrain);
    }

    public void onPlayerClick(View v) {
        Button onbtnclick = (Button) v;
        if (!(onbtnclick.getText().toString().isEmpty())) return;
        String symbol = counter % 2 == 0 ? "o" : "x";
        onbtnclick.setText(symbol);
        int index = Integer.parseInt(onbtnclick.getTag().toString());
        board.set(index, symbol);
        counter++;
        if (checkWinner(symbol)) {

            if (symbol.equals("o")) {
                player1score++;
                player1result.setText(player1score + "");
            } else {
                player2score++;
                player2result.setText(player2score + "");
            }
            resetBorder();
        } else if (counter == 9) {

            resetBorder();
        }
    }

    private void resetBorder() {
        board = new ArrayList<>(Arrays.asList(intial));
        int childcount = constraintLayout.getChildCount();
        for (int i = 0 ; i < childcount ; i++)
        {
            View view = constraintLayout.getChildAt(i);
            if (view instanceof Button)
            {
                ((Button) view).setText("");
            }
        }
        counter = 0 ;
    }

    private boolean checkWinner(String symbol) {
        // rows
        if (symbol.equals(board.get(0)) &&
                symbol.equals(board.get(1)) &&
                symbol.equals(board.get(2)))
            return true;
        if (symbol.equals(board.get(3)) &&
                symbol.equals(board.get(4)) &&
                symbol.equals(board.get(5)))
            return true;
        if (symbol.equals(board.get(6)) &&
                symbol.equals(board.get(7)) &&
                symbol.equals(board.get(8)))
            return true;
        //column
        if (symbol.equals(board.get(0)) &&
                symbol.equals(board.get(3)) &&
                symbol.equals(board.get(6)))
            return true;
        if (symbol.equals(board.get(1)) &&
                symbol.equals(board.get(4)) &&
                symbol.equals(board.get(7)))
            return true;
        if (symbol.equals(board.get(2)) &&
                symbol.equals(board.get(5)) &&
                symbol.equals(board.get(8)))
            return true;

        //x
        if (symbol.equals(board.get(0)) &&
                symbol.equals(board.get(4)) &&
                symbol.equals(board.get(8)))
            return true;
        if (symbol.equals(board.get(2)) &&
                symbol.equals(board.get(4)) &&
                symbol.equals(board.get(6)))
            return true;

        else
            return false;

    }
}