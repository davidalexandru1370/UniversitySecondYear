#pragma once
typedef int TComp;

class SLLNode
{
private:
	TComp element;
	SLLNode* next;
public:
	SLLNode(TComp value = 0, SLLNode* next = nullptr);
	~SLLNode();
	TComp get_value() const;
	SLLNode* get_next() const;
	void set_value(TComp new_element);
	void set_next(SLLNode* node);
};

