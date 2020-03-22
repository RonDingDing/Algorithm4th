class MinStack:

	def __init__(self):
		"""
		initialize your data structure here.
		"""
		self.stack = []
		self.min = []

	def push(self, x: int) -> None:
		self.stack.append(x)
		if len(self.min) == 0 or x <= self.min[-1]:
			self.min.append(x)

	def pop(self) -> None:
		if self.stack:
			top = self.stack.pop()
			if self.min and top == self.min[-1]:
				self.min.pop()
		else:
			top = None
		return top

	def top(self) -> int:
		return self.stack[-1] if self.stack else None

	def getMin(self) -> int:
		return self.min[-1] if self.stack else None


if __name__ == '__main__':
	minStack = MinStack()
	minStack.push(-1)
	minStack.push(-3)
	minStack.push(-2)
	print(minStack.getMin())

	minStack.pop()

	print(minStack.getMin())
	minStack.pop()
	print(minStack.getMin())
