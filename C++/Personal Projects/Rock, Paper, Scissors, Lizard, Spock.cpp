/*Rock, Paper, Scissors, Lizard, Spock
  Danny Nguyen
  March 24, 2018
  Rock, Paper, Scissors, Lizard, Spock is an extension of the game Rock,
  Paper, Scissors. It was created by Sam Kass and Karen Bryla, but
  inspiration for this program arose when it was directly referenced
  in the popular TV show, Big Bang Theory. As Sheldon explains, "Scissors
  cuts paper, paper covers rock, rock crushes lizard, lizard poisons
  Spock, Spock smashes scissors, scissors decapitates lizard, lizard eats
  paper, paper disproves Spock, Spock vaporizes rock, and as it always has,
  rock crushes scissors."


  Last Updated: February 23, 2020
  Note: In order to preserve the legacy of the program, original code
  remains unchanged beside comments being removed and past unnecessary
  spacings being removed for the sake of easier modern readability.
  
  Known Bugs:
  - Scissors and Paper are mixed up in the console display.
  - There is flawed logic whereas the player could choose paper while
    the computer chooses scissors and the outcome appears that player wins.
    
  What I Learned:
  In the event that this project were to be recreated or updated, I would be
  more careful with the conditional statements as to determine a winner, as
  the magnitude of if statements not only increasingly gets confusing the more 
  that exist, but they also slow down the program's functionality since each
  condition is checked sequentially. There are more efficient ways I can think of 
  now to implement this game, but in the moment in time that this was written, 
  I had a limited scope of knowledge and a lack of sense for quality control. In 
  essence, the program was a creative idea, but the execution leaves more to be desired.
*/

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

using namespace std;

int main(){
  srand(time(NULL));
  int choice, p1c, p2c, w, l;
  char cont;
  string player;
  w = 0;
  l = 0;
  cont = 'y';
  cout << "Welcome to Rock, Paper, Scissors, Lizard, Spock." << endl;
  cout << "Enter your name." << endl;
  cout << " " << endl;
  cin >> player;
  while(cont=='y'){
    cout << "______________________" << endl;
    cout << "|1.)Instructions     |" << endl;
    cout << "|2.)Play Another Game|" << endl;
    cout << "|3.)Exit             |" << endl;
    cout << "______________________" << endl;
    cout << "Wins: " << w << " " << "Losses: " << l << endl;
    cout << "" << endl;
    cin >> choice;
    cout << "" << endl;
    switch(choice){
      case 1:{
        cout << "_____________________________" << endl;
        cout << "|(1)Rock                    |" << endl;
        cout << "|(2)Scissors                |" << endl;
        cout << "|(3)Paper                   |" << endl;
        cout << "|(4)Lizard                  |" << endl;
        cout << "|(5)Spock                   |" << endl;
        cout << "|Rock crushes Scissors      |" << endl;
        cout << "|Rock crushes Lizard        |" << endl;
        cout << "|Paper covers Rock          |" << endl;
        cout << "|Paper disproves Spock      |" << endl;
        cout << "|Scissors cuts Paper        |" << endl;
        cout << "|Scissors decapitates Lizard|" << endl;
        cout << "|Lizard eats Paper          |" << endl;
        cout << "|Lizard poisons Spock       |" << endl;
        cout << "|Spock vaporizes Rock       |" << endl;
        cout << "|Spock smashes Scissors     |" << endl;
        cout << "_____________________________" << endl;
        break;
      }
      case 2:{
        cout << "___________________________________________________________" << endl;
        cout << "| (1)Rock | (2)Scissors | (3)Paper | (4)Lizard | (5)Spock |" << endl;
        cout << "___________________________________________________________" << endl;
        cout << "What will you choose?" << endl;
        cout << "" << endl;
        cin >> p1c;
        p2c = rand()%5 + 1;
        cout << "" << endl;
        if((p1c<0)||(p1c>5)){
          cout << "Invalid input." << endl;
          break;
        }
        if(p1c==1){
          cout << player << " chose {Rock}." << endl;
        }
        if(p1c==2){
          cout << player << " chose {Paper}." << endl;
        }
        if(p1c==3){
          cout << player << " chose {Scissors}." << endl;
        }
        if(p1c==4){
          cout << player << " chose {Lizard}." << endl;
        }
        if(p1c==5){
          cout << player << " chose {Spock}." << endl;
        }
        if(p2c==1){
          cout << "Computer chose {Rock}." << endl;
        }
        if(p2c==2){
          cout << "Computer chose {Paper}." << endl;
        }
        if(p2c==3){
          cout << "Computer chose {Scissors}." << endl;
        }
        if(p2c==4){
          cout << "Computer chose {Lizard}." << endl;
        }
        if(p2c==5){
          cout << "Computer chose {Spock}." << endl;
        }
        if(((p1c==1)&&(p2c==1))||((p1c==2)&&(p2c==2))||((p1c==3)&&(p2c==3))||((p1c==4)&&(p2c==4))||((p1c==5)&&(p2c==5))){
          cout << "It's a tie." << endl;
          break;
        }
        if((p1c==1)&&(p2c==2)){
          cout << "Rock crushes Scissors." << endl;
          w = w+1;
          break;
        }
        if((p1c==1)&&(p2c==3)){
          cout << "Paper covers Rock." << endl;
          l = l+1;
          break;
        }
        if((p1c==1)&&(p2c==4)){
          cout << "Rock crushes Lizard." << endl;
          w = w+1;
          break;
        }
        if((p1c==1)&&(p2c==5)){
          cout << "Spock vaporizes Rock." << endl;
          l = l+1;
          break;
        }
        if((p1c==2)&&(p2c==1)){
          cout << "Rock smashes Scissors." << endl;
          l = l+1;
          break;
        }
        if((p1c==2)&&(p2c==3)){
          cout << "Scissors cuts Paper." << endl;
          w = w+1;
          break;
        }
        if((p1c==2)&&(p2c==4)){
          cout << "Scissors decapitates Lizard." << endl;
          w = w+1;
          break;
        }
        if((p1c==2)&&(p2c==5)){
          cout << "Spock smashes Scissors." << endl;
          l = l+1;
          break;
        }
        if((p1c==3)&&(p2c==1)){
          cout << "Paper covers Rock." << endl;
          w = w+1;
          break;
        }
        if((p1c==3)&&(p2c==2)){
          cout << "Scissors cuts Paper." << endl;
          l = l+1;
          break;
        }
        if((p1c==3)&&(p2c==4)){
          cout << "Lizard eats Paper." << endl;
          l = l+1;
          break;
        }
        if((p1c==3)&&(p2c==5)){
          cout << "Paper disproves Spock." << endl;
          w = w+1;
          break;
        }
        if((p1c==4)&&(p2c==1)){
          cout << "Rock crushes Lizard." << endl;
          l = l+1;
          break;
        }
        if((p1c==4)&&(p2c==2)){
          cout << "Scissors decapitates Lizard." << endl;
          l = l+1;
          break;
        }
        if((p1c==4)&&(p2c==3)){
          cout << "Lizard eats paper." << endl;
          w = w+1;
          break;
        }
        if((p1c==4)&&(p2c==5)){
          cout << "Lizard poisons Spock." << endl;
          w = w+1;
          break;
        }
        if((p1c==5)&&(p2c==1)){
          cout << "Spock vaporizes Rock." << endl;
          w = w+1;
          break;
        }
        if((p1c==5)&&(p2c==2)){
          cout << "Paper disproves Spock." << endl;
          l = l+1;
          break;
        }
        if((p1c==5)&&(p2c==3)){
          cout << "Spock smashes Scissors." << endl;
          w = w+1;
          break;
        }
        if((p1c==5)&&(p2c==4)){
          cout << "Lizard poisons Spock." << endl;
          l = l+1;
          break;
        }
      }
      case 3:{
        cont = 'n';
        break;
      }
      default:{
        cout << "Invalid Input." << endl;
      }
    }
  }
}
