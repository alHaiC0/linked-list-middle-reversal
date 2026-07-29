import ch02.stacks.LinkedStack;

import support.LLNode;

public class LinkedListMR<T>{
   
   LLNode<T> head; //head of linked list
   LLNode<T> currNode; //node used for iteration and track current node
   LLNode<T> top; //top of linked list stack 
   
   LLNode<T> prevNode; //node keeps track of previous
   LLNode<T> nextNode; //node keeps track of next
   
   LinkedStack<T> lStack; //linked stack for performing operations 
   T temp; //store info from elements temporarily or perform operations
   
   //constructor
   public LinkedListMR(){
      head = null;
   }
   
   //isEmpty - nice to have
   public boolean isEmpty(){              
    return (head == null); 
   }
   
   //adds
   //add node at head 
   public void addHead(T element){
      LLNode<T> newNode = new LLNode<T>(element);
      newNode.setLink(head);
      head = newNode; 
   }
   
   //add node in between
   public void addBetween(int index, T element){
      LLNode<T> newNode = new LLNode<T>(element);
      currNode = head;
      
      //get to index
      for(int i = 0; i < index; i++){
         currNode = currNode.getLink();
         if(currNode.getLink() == null){
            System.out.println("Linked List is NOT long enough!");
            return;
         }
      }
      
      currNode.setLink(newNode);
      newNode.setLink(null);
   }
   
   //general add to the end of the list 
   public void add(T element){
      LLNode<T> newNode = new LLNode<T>(element);
      currNode = head;
      
      //if adding to an empty list 
      if(isEmpty()){
         newNode.setLink(head);
         head = newNode; 
      //get to end if necessary
      }else{
         while(currNode.getLink() != null){
            currNode = currNode.getLink();
         }
         currNode.setLink(newNode);
      }
      
   }
   
   //toString
   public String toString(){
      StringBuilder stringb = new StringBuilder(""); 
      if(isEmpty()){
         stringb.append("LinkedList is empty!");
      }else{
         stringb.append("Linked List: ");
         currNode = head;
         while(currNode.getLink() != null){
            stringb.append(currNode.getInfo() + ", ");
            currNode = currNode.getLink();
         }
         if(currNode.getLink() == null){
            stringb.append(currNode.getInfo());
         }  
      }
         
      String print = stringb.toString(); 
      return print;
   }
   
   //finding the middle of a Linked List
   public T findMiddle(){
      LLNode<T> slowPointNode = head;
      LLNode<T> fastPointNode = head;
      
      while(fastPointNode != null && fastPointNode.getLink() != null){
         //move slow pointer
         slowPointNode = slowPointNode.getLink();
         
         //move fast pointer
         fastPointNode = fastPointNode.getLink();
         fastPointNode = fastPointNode.getLink();
      }
      
      return slowPointNode.getInfo();
   }

   //reversing the linked list 
   //with a stack - taking the *data* rather than the actual node
   public void reverseStack(){
      lStack = new LinkedStack<T>();
      currNode = head;
      //push entire LL onto stack 
      while(currNode.getLink() != null){ 
         lStack.push(currNode.getInfo());
         currNode = currNode.getLink();
      }
      //while doenst get last node
      if(currNode.getLink() == null){
         lStack.push(currNode.getInfo());
         currNode = currNode.getLink();
      }
      
      head = currNode;
      
      //pop entire LL stack and remake LL 
      while(!lStack.isEmpty()){
         temp = lStack.top();
         add(temp);
         lStack.pop();
      }
   }
   
   //reversing the linked list 
   //"in place"
   public void reverseInPlace(){
      //set values
      currNode = head;
      prevNode = null; 
      
      //iterate through, reassigning links
      while(currNode.getLink() != null){
         //assign value to next 
         nextNode = currNode.getLink();
         
         //set the pointer of the current node to the previous node
         currNode.setLink(prevNode); 
      
         prevNode = currNode;
         currNode = nextNode;      
      }
      //while doesnt get last node
      if(currNode.getLink() == null){
         //assign value to next 
         nextNode = currNode.getLink();
         
         //set the pointer of the current node to the previous node
         currNode.setLink(prevNode); 
      
         prevNode = currNode;
         currNode = nextNode; 
      }
      
      //update the head
      head = prevNode;
      
   }
}