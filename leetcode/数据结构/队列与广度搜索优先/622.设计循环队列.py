# 622. 设计循环队列
# 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。

# 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。

# 你的实现应该支持如下操作：

# MyCircularQueue(k): 构造器，设置队列长度为 k 。
# Front: 从队首获取元素。如果队列为空，返回 -1 。
# Rear: 获取队尾元素。如果队列为空，返回 -1 。
# enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
# deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
# isEmpty(): 检查循环队列是否为空。
# isFull(): 检查循环队列是否已满。
 

# 示例：

# MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
# circularQueue.enQueue(1);  // 返回 true
# circularQueue.enQueue(2);  // 返回 true
# circularQueue.enQueue(3);  // 返回 true
# circularQueue.enQueue(4);  // 返回 false，队列已满
# circularQueue.Rear();  // 返回 3
# circularQueue.isFull();  // 返回 true
# circularQueue.deQueue();  // 返回 true
# circularQueue.enQueue(4);  // 返回 true
# circularQueue.Rear();  // 返回 4
 

# 提示：

# 所有的值都在 0 至 1000 的范围内；
# 操作数将在 1 至 1000 的范围内；
# 请不要使用内置的队列库。


class MyCircularQueue:
    def __init__(self, k: int):
        """
        Initialize your data structure here. Set the size of the queue to be k.
        """
        self.queue = [None for _ in range(k)]
        self.head = -1
        self.tail = -1
        self.length = 0

    def enQueue(self, value: int) -> bool:

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

        else:
            self.queue[self.head] = None
            if self.head == self.tail:
                self.head = -1
                self.tail = -1
            else:
                self.head = (self.head + 1) % len(self.queue)
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


if __name__ == "__main__":
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
