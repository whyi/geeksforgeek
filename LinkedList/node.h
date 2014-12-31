#include <iostream>
#include <initializer_list>

template <typename T>
struct node {
	T data;
	// use shared_ptr or something to not worry about it in the future
	node<T> *prev;
	node<T> *next;

	node(const T& data) : data(data), prev(nullptr), next(nullptr) {}
};

template <typename T> node<T>* construct_singly_linked_list_from(std::initializer_list<T> data)
{
	node<T> *root = new node<T>(*data.begin());

	auto itr = data.begin();
	++itr;

	node<T> *prev = root;

	while(itr != data.end())
	{
		node<T> *current_node = new node<T>(*itr);
		prev->next = current_node;
		++itr;
		prev = current_node;
	}
	return root;	
}

template <typename T>
static void print_nodes(const node<T>* node)
{
	while (true)
	{
		if (node == nullptr)
		{
			break;
		}

		std::cout << node->data << " ";
		node = node->next;
	}
	std::cout << std::endl;
}