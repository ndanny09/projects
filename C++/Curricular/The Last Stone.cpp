/*The Last Stone (Partner Project)
  Dylan Boyette & Danny Nguyen
  April 10, 2018
  User can either choose to play against another player or against the
  computer. Each player can only take 1-3 stones on their turn. The
  player who takes the last stone on their turn loses the round.
  Players play until one wins three rounds to be declared the winner.

  Last Updated: February 23, 2020
  Note: In order to preserve the legacy of the program, original code
  remains unchanged beside comments being removed and past unnecessary
  spacings being removed for the sake of easier modern readability.
*/

#include <iostream>
#include <stdio.h>
#include<stdlib.h>
#include<time.h>
using namespace std;

int stones = 10;
int playerturn = 0;
int P1W = 0;
int P2W = 0;
int winner = 0;
string PlayerOne, PlayerTwo;

void menu(){
  cout << "__________________________" << endl;
  cout << "|*    The Last Stone    *|" << endl;
  cout << "|------------------------|" << endl;
  cout << "|1.)Play Against Player  |" << endl;
  cout << "|2.)Play Against Computer|" << endl;
  cout << "|3.)Exit                 |" << endl;
  cout << "|________________________|" << endl;
}

void determineWinner(){
  if(P1W>P2W){
    cout << PlayerOne << " Wins: " << P1W << " " << PlayerTwo << " Wins: " << P2W << endl;
    cout << PlayerOne << " wins the game!" << endl;
  }else{
    cout << PlayerOne << " Wins: " << P1W << " " << PlayerTwo << " Wins: " << P2W << endl;
    cout << PlayerOne << " wins the game!" << endl;
  }
}

void playGame(int take){
  if((take<0)||(take==0)||(take>3) ){
    cout << "Invalid input. You must choose a number of stones to take between 1-3." << endl;
  }
  if((take>0)&&(take<=3)&&((stones-take)>=0)){
    stones = stones - take;
    cout << "There are now (" << stones << ") stones remaining in the pile." << endl;
    if(stones==0){
      if((playerturn%2)!=0){
        cout << PlayerOne << " wins!" << endl;
        P1W++;
      }else{
        cout << PlayerTwo << " wins!" << endl;
        P2W++;
      }
      if(P1W==3||P2W==3){
      determineWinner();
      }else{
      stones = 10;
      }
    }
    playerturn++;
  }else{
    cout << "An error has occurred. Please try again." << endl;
  }
}

void playerTurn(){
  if(PlayerTwo!="Computer"){
    int take;
    if((playerturn%2)==0){
      cout << PlayerOne << ":" << endl;
      cout << "How many stones would you like to take?" << endl;
      cin >> take;
      playGame(take);
    }
    if((playerturn%2)>0){
      cout << PlayerTwo << ":" << endl;
      cout << "How many stones would you like to take?" << endl;
      cin >> take;
      playGame(take);
    }
  }else{
    int take;
    int computertake;
    if((playerturn%2)==0){
      cout << PlayerOne << ":" << endl;
      cout << "How many stones would you like to take?" << endl;
      cin >> take;
      playGame(take);
    }
    if((playerturn%2)>0){
      if(stones>=3){
        computertake = rand()%3+1;
      }
      if(stones==2){
        computertake = rand()%2+1;
      }
      if(stones==1){
        computertake = 1;
      }
      cout << "The computer takes " << computertake << " stones." << endl;
      playGame(computertake);
    }
  }
}

int main(){
  srand(time(NULL));
  int choice;
  menu();
  cin >> choice;
  cout << "" << endl;
  switch(choice){
    case 1:{
      cout << "Enter Player 1's Name:" << endl;
      cin >> PlayerOne;
      cout << "Enter Player 2's Name:" << endl;
      cin >> PlayerTwo;
      while(P1W!=3&&P2W!=3){
        playerTurn();
      }
      break;
    }
    case 2:{
      cout << "Enter Player 1's Name:" << endl;
      cin >> PlayerOne;
      PlayerTwo = "Computer";
      while(P1W!=3&&P2W!=3){
        playerTurn();
      }
      break;
    }
    case 3:{
      break;
    }
  }
}
