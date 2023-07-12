package com.mparee.bakibz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.mparee.bakibz.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bu1
import kotlinx.android.synthetic.main.activity_main.bu2
import kotlinx.android.synthetic.main.activity_main.bu3
import kotlinx.android.synthetic.main.activity_main.bu4
import kotlinx.android.synthetic.main.activity_main.bu5
import kotlinx.android.synthetic.main.activity_main.bu6
import kotlinx.android.synthetic.main.activity_main.bu7
import kotlinx.android.synthetic.main.activity_main.bu8
import kotlinx.android.synthetic.main.activity_main.bu9
import kotlinx.android.synthetic.main.activity_main.replaybtn
import kotlinx.android.synthetic.main.activity_mainmedium.*
import kotlinx.android.synthetic.main.activity_mainplay.*
import java.lang.Exception
import kotlin.concurrent.thread
import kotlin.random.Random

class MainMediumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmedium)
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
            //busel.text = "X"
            busel.setBackgroundResource(R.color.colorPlayer1)
            player1.add(cellId)
            activeplayer=2
            Handler().postDelayed({
                autoplay(busel)
            }, 1000)
        }
        else {
           // busel.text = "0"
            busel.setBackgroundResource(R.color.colorPlayer2)
            player2.add(cellId)
            activeplayer = 1
        }
        busel.isEnabled = false
        checkwinner(busel)
    }

    fun checkwinner(busel: Button): Int {
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
        if(winner!=-1) {
            when (winner) {
                1 -> {
                  val toast=Toast.makeText(this, "You Win", Toast.LENGTH_LONG)
                    toast.show()
                    toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                    busel.isEnabled=false
                    busel.isClickable = false
                    Handler().postDelayed({
                        replay()
                    }, 900)
                }
                2 -> {
                  val toast= Toast.makeText(this, "Computer Win", Toast.LENGTH_LONG)
                    toast.show()
                    toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                    busel.isEnabled=false
                    busel.isClickable = false
                    Handler().postDelayed({
                        replay()
                    }, 900)
                }
              else -> {
                   val toast= Toast.makeText(this, "Draw", Toast.LENGTH_LONG)
                  toast.show()
                  toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                  busel.isEnabled=false
                    busel.isClickable = false
                  Handler().postDelayed({
                      replay()
                  }, 900)
                }
            }
        }
        return winner
    }

    private fun replay(){
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
    }

   fun autoplay(busel: Button) {
        val emptycell = ArrayList<Int>()
       for(cellId in 1..9) {
           if (player1.contains(1) && player1.contains(2)) {
               emptycell.add(3)
           }
           if (player1.contains(4) && player1.contains(5)) {
               emptycell.add(6)
           }
           if (player1.contains(7) && player1.contains(8)) {
               emptycell.add(9)
           }
           if (player1.contains(1) && player1.contains(4)) {
               emptycell.add(7)
           }
           if (player1.contains(2) && player1.contains(5)) {
               emptycell.add(8)
           }
           if (player1.contains(3) && player1.contains(6)) {
               emptycell.add(9)
           }
           if (!(player1.contains(cellId) || player2.contains(cellId))) {
                       emptycell.add(cellId)
           }
           busel.isEnabled = false
       }

           val r = java.util.Random()
           val randIndex = r.nextInt(emptycell.size - 0) + 0
           val cellId = emptycell[randIndex]



        val busel: Button?
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
}


