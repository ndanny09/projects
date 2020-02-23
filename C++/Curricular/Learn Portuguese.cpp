/*Learn Portuguese
  Danny Nguyen
  April 30, 2018
  User is prompted to learn five words in Portuguese and type the
  translation back to the system. There is an option for a word bank
  included with the quiz. Each correct answer gets one point. The
  maximum score is five points.

  Last Updated: February 23, 2020
  Note: In order to preserve the legacy of the program, original code
  remains unchanged beside comments being removed and past unnecessary
  spacings being removed for the sake of easier modern readability.

  What I Learned:
  Since this project was submitted as a final exam for my introductory
  to programming course, I demonstrated how distinct data types and variables
  can interact with each other in the system, such as how useful comparing
  two different arrays' values can be to check user input answers. Each
  different concept I learned throughout that year was able to be included
  in the program with the exclusion of a double variable. The only
  notable change I would make to the program today is to camel-case the
  variables as well as make some minor changes to the variable names to be
  more self commenting.
*/

#include <iostream>
using namespace std;

int menuoption;
int score = 0;
char quiza, wordbank;
string answer[5] = {"Great", "Hello", "No", "Bye", "Yes"};
string translation[5];

void menu(){
  cout << "____________________________________" << endl;
  cout << "|Welcome to the Language Translator|" << endl;
  cout << "|__________________________________|" << endl;
  cout << "|Learn common phrases in Portuguese|" << endl;
  cout << "|------(Test your knowledge)-------|" << endl;
  cout << "____________________________________" << endl;
  cout << "1.)Learn Portuguese" << endl;
  cout << "2.)Exit" << endl;
  cout << "" << endl;
}

void quizq(){
  cout << "(Quiz)" << endl;
  cout << "1.)Enter English Translation For: Otimo" << endl;
  cout << "2.)Enter English Translation For: Oi" << endl;
  cout << "3.)Enter English Translation For: Nao" << endl;
  cout << "4.)Enter English Translation For: Tchau" << endl;
  cout << "5.)Enter English Translation For: Sim" << endl;
  for(int i=0;i<5;i++){
    cout << "" << endl;
    cout << "Enter your answer." << endl;
    cin >> translation[i];
  }
  for(int i=0;i<5;i++){
    if((answer[i])==(translation[i])){
      score = score + 1;
    }
  }
  cout << "" << endl;
  cout << "Your score is " << score << "/5." << endl;
  cout << "" << endl;
  if(score==5){
    cout << "Congratulations. You have answered all of them correctly." << endl;
  }
  if(score!=5){
    cout << "Please try again." << endl;
  }
}

void quiz()
{
  cout << "Are you ready to take a quiz? (y/n)" << endl;
  cin >> quiza;
  cout << "" << endl;
  if(quiza=='y'){
    cout << "To help with translation, would you like a word bank? (y/n)" << endl;
    cin >> wordbank;
    cout << "" << endl;
    if(wordbank=='y'){
      cout << "Word Bank" << endl;
      cout << "______________________________" << endl;
      cout << "Hello | Yes | No | Great | Bye" << endl;
      cout << "______________________________" << endl;
      cout << "" << endl;
      quizq();
    }
    if(wordbank=='n'){
      cout << "" << endl;
      quizq();
    }
  }
}

void choice(){
  cin >> menuoption;
  switch(menuoption){
    case 1:{
      cout << "______________________________________________________" << endl;
      cout << "Here is a list of common Portuguese words." << endl;
      cout << "1.)Oi" << endl;
      cout << "2.)Sim" << endl;
      cout << "3.)Nao" << endl;
      cout << "4.)Otimo" << endl;
      cout << "5.)Tchau" << endl;
      cout << "______________________________________________________" << endl;
      cout << "" << endl;
      cout << "Meaning of the words listed above:" << endl;
      cout << "Hello | Yes | No | Great | Bye" << endl;
      cout << "" << endl;
      quiz();
    }
    case 2:{
      break;
    }
    default:{
      break;
    }
  }
}

int main(){
  menu();
  choice();
}
