template <typename T>
struct node {
	T data;
	// use shared_ptr or something to not worry about it in the future
	node<T> *prev;
	node<T> *next;

	node(const T& data) : data(data) {}
};

