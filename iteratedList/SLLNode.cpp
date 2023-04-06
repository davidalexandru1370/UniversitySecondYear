#include "SLLNode.h"

SLLNode::SLLNode(TComp value, SLLNode* next)
{
	this->element = value;
	this->next = next;
}

SLLNode::~SLLNode()
{
	//delete this;
}

TComp SLLNode::get_value()  const
{
	return this->element;
}

SLLNode* SLLNode::get_next() const
{
	return this->next;
}

void SLLNode::set_value(TComp new_element)
{
	this->element = new_element;
}

void SLLNode::set_next(SLLNode* node)
{
	this->next = node;
}


