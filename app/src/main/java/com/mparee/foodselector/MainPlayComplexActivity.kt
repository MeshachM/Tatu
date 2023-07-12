package com.mparee.bakibz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.mparee.bakibz.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.replaybtn
import kotlinx.android.synthetic.main.activity_maincomplexplay.*
import kotlin.random.Random

class MainPlayComplexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maincomplexplay)
        replaybtn.setOnClickListener {
            replay()
        }
    }

    fun buclick(view: View) {
        val busel = view as Button
        var cellId = 0
        when (busel.id) {
            R.id.bu1 -> cellId = 1
            R.id.bu2 -> cellId = 2
            R.id.bu3 -> cellId = 3
            R.id.bu4 -> cellId = 4
            R.id.bu5 -> cellId = 5
            R.id.bu6 -> cellId = 6
            R.id.bu7 -> cellId = 7
            R.id.bu8 -> cellId = 8
            R.id.bu9 -> cellId = 9
        }
        //  Toast.makeText(this, "ID:"+ cellId, Toast.LENGTH_LONG).show()
        playGame(cellId, busel)
    }

    var player1 = arrayListOf<Int>()

    var player2 = arrayListOf<Int>()
    var activeplayer = 1
    fun playGame(cellId: Int, busel: Button) {
        if (activeplayer == 1) {
            // busel.text = "X"
            busel.setBackgroundResource(R.color.colorPlayer1)
            player1.add(cellId)
            activeplayer = 2
            // autoplay()
        } else {
            //  busel.text = "0"
            busel.setBackgroundResource(R.color.colorPlayer2)
            player2.add(cellId)
            activeplayer = 1
        }
        busel.isEnabled = false
        checkwinner()
    }

    fun checkwinner(): Int {
        var winner = -1
        //Row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //Row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //Row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        //Cols
        //Col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //Col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //Col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        //Diagonals1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        //Diagonals2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner != -1) {
            if (winner == 1) {
                val toast = Toast.makeText(this, "Player 1 Win", Toast.LENGTH_LONG)
                toast.show()
                toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                replay()
            }
            if (winner == 2) {
                val toast = Toast.makeText(this, "Player 2 Win", Toast.LENGTH_LONG)
                toast.show()
                toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                replay()
            }
            if (winner != 1 && winner != 2) {
                val toast = Toast.makeText(this, "No Winner", Toast.LENGTH_LONG)
                toast.show()
                toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                replay()
            }
        }
        return winner
    }

    private fun replay() {
        recreate()
        /*finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)*/
    }
}


    //in this var we will store the computersMove


    //this is our minimax function to calculate
    //the best move for the computer
   /* var player2: cellId? = null
    fun minimax(cellId: Int, busel: Button): Int {
        if (winner==1) return +1
        if (winner==2) return -1

        if (availableCells.isEmpty()) return 0

        var min = Integer.MAX_VALUE
        var max = Integer.MIN_VALUE

        for (i in availableCells.indices) {
            val cellId = availableCells[i]
            if (player == player2) {
                placeMove(cellId, player2)
                val currentScore = minimax(cellId + 1, player1)
                max = Math.max(currentScore, max)

                if (currentScore >= 0) {
                    if (cellId == 0) activeplayer2 = cell
                }

                if (currentScore == 1) {
                    board[cell.i][cell.j] = ""
                    break
                }

                if (i == availableCells.size - 1 && max < 0) {
                    if (depth == 0) activeplayer2 = cell
                }

            } else if (player == PLAYER) {
                placeMove(cell, PLAYER)
                val currentScore = minimax(depth + 1, COMPUTER)
                min = Math.min(currentScore, min)

                if (min == -1) {
                    board[cell.i][cell.j] = ""
                    break
                }
            }
            board[cell.i][cell.j] = ""
        }

        return if (player == COMPUTER) max else min
    }

    //this function is placing a move in the given cell
    fun placeMove(cell: Cell, player: String) {
        board[cell.i][cell.j] = player
    }

}

/*
Toast toast=Toast.makeText(getApplicationContext(),"This is advanced toast",Toast.LENGTH_LONG);
toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT,0,0);
View view=toast.getView();
TextView  view1=(TextView)view.findViewById(android.R.id.message);
view1.setTextColor(Color.YELLOW);
view.setBackgroundResource(R.color.colorPrimary);
toast.show();
 */
  /*  fun autoplay() {
        var emptycell = ArrayList<Int>()
        for (cellId in 1..9) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                emptycell.add(cellId)
            }
        }

        var r = java.util.Random()
        var randIndex = r.nextInt(emptycell.size - 0) + 0
        var cellId = emptycell[randIndex]

        var busel: Button?
        when (cellId) {
            1 -> busel = bu1
            2 -> busel = bu2
            3 -> busel = bu3
            4 -> busel = bu4
            5 -> busel = bu5
            6 -> busel = bu6
            7 -> busel = bu7
            8 -> busel = bu8
            9 -> busel = bu9
            else -> {
                busel = bu1
            }
        }
        playGame(cellId, busel)
    }*/
    //unbeaten tictoc
    /*
     fun minimax(cellId: Int, busel: Button)
     {
        if (winner = self.getWinner(player1)): Player
      {
            return winner.rawValue * player.rawValue // -1 * -1 || 1 * 1
        }

        var move = -1
        var score = -2
        for (cellId in 1..9) { // For all moves
            if (player2[cellId] == Player.Blank){ // Only possible moves
                var boardWithNewMove = player2 // Copy board to make it mutable
                boardWithNewMove[cellId] = player1 // Try the move
                var scoreForTheMove = -self.minimax(boardWithNewMove, player1: self.getponnentFor(player1)) // Count negative score for oponnent
                if (scoreForTheMove > score) {
                    score = scoreForTheMove
                    move = cellId
                } // Picking move that gives oponnent the worst score
            }
        }
        if (move == -1) {
            return 0 // No move - it's a draw
        }
        return score
    }
    //unbeaten tictoc
    /*private func minimax(board: Array<Player>, player: Player) -> Int {
        if let winner = self.getWinner(board) as Player {
            return winner.rawValue * player.rawValue // -1 * -1 || 1 * 1
        }

        var move = -1
        var score = -2

        for var i = 0; i < 9; ++i { // For all moves
            if board[i] == Player.Blank { // Only possible moves
                var boardWithNewMove = board // Copy board to make it mutable
                boardWithNewMove[i] = player // Try the move
                let scoreForTheMove = -self.minimax(boardWithNewMove, player: self.getOponnentFor(player)) // Count negative score for oponnent
                if scoreForTheMove > score {
                    score = scoreForTheMove
                    move = i
                } // Picking move that gives oponnent the worst score
            }
        }
        if move == -1 {
            return 0 // No move - it's a draw
        }
        return score
   }*/
    */
*/


