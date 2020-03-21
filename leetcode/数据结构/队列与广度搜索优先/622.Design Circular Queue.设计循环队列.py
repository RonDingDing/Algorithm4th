class MyCircularQueue:

	def __init__(self, k: int):
		"""
		Initialize your data structure here. Set the size of the queue to be k.
		"""
		self.queue = [None for _ in range(k )]
		self.head = -1
		self.tail = -1
		self.length = 0

	def enQueue(self, value:int) -> bool:


		if self.isFull():
			tag = False
		else:
			if self.isEmpty():
				self.head = 0
			self.tail = (self.tail + 1) % len(self.queue)
			self.queue[self.tail] = value
			self.length += 1
			tag = True
		return tag


	def Front(self) -> int:
		"""
		Get the front item from the queue.
		"""
		if not self.isEmpty():
			return self.queue[self.head]
		return -1

	def deQueue(self):
		if self.isEmpty():
			tag = False


		else :
			self.queue[self.head] = None
			if (self.head == self.tail):
				self.head = -1
				self.tail = -1
			else:
				self.head= (self.head + 1) % len(self.queue)
			self.length -= 1
			tag = True


		return tag

	def Rear(self) -> int:
		"""
		Get the last item from the queue.
		"""
		if not self.isEmpty():
			return self.queue[self.tail]
		return -1

	def isEmpty(self) -> bool:
		"""
		Checks whether the circular queue is empty or not.
		"""
		return self.length == 0

	def isFull(self) -> bool:
		"""
		Checks whether the circular queue is full or not.
		"""
		return self.length == len(self.queue)

if __name__ == '__main__':
	q = MyCircularQueue(3)
	q.enQueue(4)
	print(q.queue, q.head, q.tail, q.length)
	q.enQueue(9)
	print(q.queue, q.head, q.tail, q.length)
	q.enQueue(5)
	print(q.queue, q.head, q.tail, q.length)
	q.enQueue(5)
	print(q.queue, q.head, q.tail, q.length)
	q.deQueue()
	print(q.queue, q.head, q.tail, q.length)
	q.deQueue()
	print(q.queue, q.head, q.tail, q.length)

	q.enQueue(6)
	print(q.queue, q.head, q.tail, q.length)
	q.enQueue(4)
	print(q.queue, q.head, q.tail, q.length)
	q.enQueue(7)
	print(q.queue, q.head, q.tail, q.length)
	q.deQueue()
	print(q.queue, q.head, q.tail, q.length)
