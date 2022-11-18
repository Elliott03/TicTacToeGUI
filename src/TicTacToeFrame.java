import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TicTacToeFrame extends JFrame
{
    JPanel mainPnl;
    JPanel statsPnl;
    JPanel gamePnl;
    JPanel controlPnl;
    
    JLabel currentPlayerLbl;

    JButton quitBtn;



    private static final int ROW = 3;
    private static final int COL = 3;
    private static JButton[][] board = new JButton[ROW][COL];

    String currentPlayer = "X";



    public TicTacToeFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createStatsPanel();
        mainPnl.add(statsPnl, BorderLayout.NORTH);

        createDisplayPanel();
        mainPnl.add(gamePnl, BorderLayout.CENTER);

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        TicTacToe.clearBoard();
    }

    private void createStatsPanel()
    {
        statsPnl = new JPanel();
        currentPlayerLbl = new JLabel("Current Player:" + currentPlayer + " ");

        statsPnl.add(currentPlayerLbl);
    }

    private void createDisplayPanel()
    {
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(3, 3));
        createButtons();
    }

    private void createControlPanel()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 1));

        quitBtn = new JButton("Quit");
        controlPnl.add(quitBtn);
        quitBtn.addActionListener(ActiveEvent_ae -> System.exit(0));
    }

    public void createButtons()
    {
        for(int row = 0; row <=2; row++)
        {
            for(int col = 0; col <= 2; col++)
            {
                board[row][col] = new TicTacToeTile(row, col);
                gamePnl.add(board[row][col]);
                board[row][col].setText(" ");
                board[row][col].setBackground(Color.white);
                board[row][col].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TicTacToeTile buttonClicked = (TicTacToeTile) e.getSource();

                        JFrame frame = new JFrame("JOptionPane");
                        if (!buttonClicked.getText().isBlank()) {
                            // Show the error msg option pane
                            JOptionPane.showMessageDialog(frame, "This move is not valid");
                            return;
                        }


                        buttonClicked.setText(String.valueOf(currentPlayer));


                        TicTacToe.updateBoard(currentPlayer, buttonClicked.getRow(), buttonClicked.getCol());


                        if (TicTacToe.isWin(currentPlayer))
                        {
                            JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                            TicTacToe.clearBoard();
                            for(int row = 0; row <=2; row++) {
                                for (int col = 0; col <= 2; col++) {
                                    board[row][col].setText(" ");
                                }
                            }
                            currentPlayer = "O";
                            currentPlayerLbl.setText("Current Player:" + currentPlayer + " ");
                        }


                        if (TicTacToe.isTie())
                        {
                            JOptionPane.showMessageDialog(frame, "The game is a tie! Play again!");
                            TicTacToe.clearBoard();
                            for(int row = 0; row <=2; row++) {
                                for (int col = 0; col <= 2; col++) {
                                    board[row][col].setText(" ");
                                }
                            }
                            currentPlayer = "O";
                            currentPlayerLbl.setText("Current Player:" + currentPlayer + " ");
                        }

                            if (currentPlayer == "X") {
                                currentPlayer = "O";
                            } else {
                                currentPlayer = "X";
                            }
                            currentPlayerLbl.setText("Current Player:" + currentPlayer + " ");
                    }
                });
            }
        }
    }
}
