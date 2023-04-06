#include "ListIterator.h"
#include "SortedIndexedList.h"
#include <iostream>
using namespace std;
#include <exception>

SortedIndexedList::SortedIndexedList(Relation r) {
	//TODO - Implementation
	length = 0;
	relation = r;
	head = nullptr;
	//head = new SLLNode();
}

//Theta(1)
int SortedIndexedList::size() const {
	//TODO - Implementation
	return length;
}

//Theta(1)
bool SortedIndexedList::isEmpty() const {
	//TODO - Implementation
	return length == 0;
}

//Theta(i) - worst case 
//Theta(i) - average case
//Theta(i) - best case
//O(i) - total case
TComp SortedIndexedList::getElement(int i) const {
	//TODO - Implementation
	if (i >= length)
	{
		throw std::exception();
	}
	SLLNode* result = head;

	while (i--)
	{
		result = result->get_next();
	}

	return result->get_value();
}

//Theta(i) - worst case
//Theta(i) - average case
//Theta(i) - best case
//Total case - Theta(i)
TComp SortedIndexedList::remove(int i) {
	//TODO - Implementation
	//return NULL_TCOMP;
	if (i >= length || i < 0)
	{
		throw exception();
	}

	int index = 0;
	SLLNode* previous = head;
	while (index < (i - 1))
	{
		previous = previous->get_next();
		index++;
	}
	this->length--;
	TComp saved;
	if (previous->get_next() == nullptr)
	{//last element
		saved = previous->get_value();
		previous->set_next(nullptr);
		// ?
	}//first element
	if (previous == head)
	{
		if (i == 0)
		{
			SLLNode* remember = head->get_next();
			saved = previous->get_value();
			delete head;
			head = remember;
		}
		else
		{
			SLLNode* remember = previous->get_next();
			saved = previous->get_next()->get_value();
			previous->set_next(previous->get_next()->get_next());
			//deallocate part
			remember->set_next(nullptr);
			delete remember;
		}
	}//between elements
	else {
		SLLNode* remember = previous->get_next();
		saved = previous->get_next()->get_value();
		previous->set_next(previous->get_next()->get_next());
		//deallocate part
		remember->set_next(nullptr);
		delete remember;
	}
	return saved;
}

//Theta(n) - worst case
//Theta(n) - average case
//Theta(1) - best case
//Total case - O(n)
int SortedIndexedList::search(TComp e) const {
	//TODO - Implementation
	int index = 0;
	SLLNode* result = head;
	while (result != nullptr)
	{
		if (result->get_value() == e)
		{
			return index;
		}
		result = result->get_next();
		index++;
	}
	return -1;
}

//Theta(n) - worst case
//Theta(n) - average case
//Theta(1) - best case
//Total case - O(n)
void SortedIndexedList::add(TComp e) {
	//TODO - Implementation
	SLLNode* position = head;
	SLLNode* prev = nullptr;
	this->length++;
	//beginnig of the list and the list is not empty
	if (position != nullptr && relation(position->get_value(), e) == false)
	{
		SLLNode* new_node = new SLLNode(e, position);
		head = new_node;
		return;
	}

	while (position != nullptr && relation(position->get_value(), e))
	{
		prev = position;
		position = position->get_next();
	}
	if (position == nullptr)
	{
		//beginning of the list and list is empty
		if (prev == nullptr)
		{
			SLLNode* new_node = new SLLNode(e, position);
			head = new_node;
		}
		else
		{   //final of the list
			SLLNode* new_node = new SLLNode(e, nullptr);
			prev->set_next(new_node);
		}
	}
	else { //between elements of the list
		SLLNode* new_node = new SLLNode(e, position);
		prev->set_next(new_node);
	}
}

ListIterator SortedIndexedList::iterator() {
	return ListIterator(*this);
}

//destructor
SortedIndexedList::~SortedIndexedList() {
	//TODO - Implementation
	SLLNode* node;
	while (head != nullptr)
	{
		node = head;
		head = head->get_next();
		delete node;
	}
}

//keeps in the SortedList only the elements that respect the given condition
//Worst case - Theta(i^2)
//Average case - Theta(i^2)
//Best case - Theta(1)
//Total case - O(n^2)
void SortedIndexedList::filter(Condition cond)
{
	SLLNode* current = head;
	SLLNode* prev = nullptr;
	int index = 0;

	while (current != nullptr)
	{
		if (current != nullptr)
		{
			prev = current;
			current = current->get_next();
		}

		if (current != nullptr && cond(current->get_value()) == true)
		{
			this->remove(index);
		}

		else {
			index++;
		}
	}

}



