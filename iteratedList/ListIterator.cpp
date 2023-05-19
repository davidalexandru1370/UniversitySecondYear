#include "ListIterator.h"
#include "SortedIndexedList.h"
#include <iostream>

using namespace std;
//Theta(1)
ListIterator::ListIterator(const SortedIndexedList& list) : list(list) {
	pos = 0;
	//TODO - Implementation
}

//Theta(1)
void ListIterator::first() {
	pos = 0;
	//TODO - Implementation
}

//Theta(1)
void ListIterator::next() {
	if (valid() == false)
	{
		throw exception();
	}
	pos++;
	//TODO - Implementation
}

//Theta(1)
bool ListIterator::valid() const {
	//TODO - Implementation
	return pos >= list.length ? false : true;
}

//Theta(i)
TComp ListIterator::getCurrent() const {
	//TODO - Implementation
	//return NULL_TCOMP;
	if (pos >= list.length)
	{
		throw std::exception();
	}
	return list.getElement(pos);
}


