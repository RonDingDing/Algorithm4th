# Leetcode 上，以Python列表为基础的队列运行比较快。

class ListQueue:
	def __init__(self):
		self.q = []

	def __str__(self):
		return str(self.q)

	def empty(self):
		return not len(self.q)

	def put(self, value):
		self.q.insert(0, value)

	def get(self):
		return self.q.pop() if not self.empty() else None


class Node:
	def __init__(self, value, next_e):
		self.value = value
		self.next = next_e

	def __eq__(self, other):
		if isinstance(other, type(self)):
			return self.value == other.value and self.next == other.next


class LinkedListQueue:
	def __init__(self):
		self.first = self.last = Node(None, None)
		self.leng = 0

	def __str__(self):
		now = self.first
		string = "["
		while now.next != None:
			string += str(now.value) + ', '
			now = now.next
		string += str(now.value)
		string += "]"
		return string

	def put(self, value):
		new_node = Node(value, None)
		self.last.next = new_node
		self.last = new_node
		self.leng += 1

	def empty(self):
		return self.leng == 0

	def get(self):
		node = self.first.next
		answer = node.value
		self.first.next = node.next
		self.leng -= 1
		return answer
