template <typename T>
struct node {
	T data;
	// use shared_ptr or something to not worry about it in the future
	node<T> *prev;
	node<T> *next;

	node(const T& data) : data(data) {}
};

template <typename T> node<T>* construct_singly_linked_list_from(std::initializer_list<T> data)
{
	// convert to lambda later like
	// std::for_each(data.begin(), data.end(), [](T data) ->
	std::static_assert(!data.empty());
	const node<T> *root = new node<T>(*data.begin());

	auto itr = data.begin();
	++itr;

	const node<T> *prev = root;

	while(itr != data.end())
	{
		const node<T> *node = new node<T>(*itr);
		prev->next = node;
		++itr;
		prev = node;
	}
	return root;	
}
