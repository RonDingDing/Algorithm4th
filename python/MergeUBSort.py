class MergeUB:
    def __init__(self, array):
        self.sort_len(array)

    def sort_len(self, array):
        self.list = [None] * len(array)
        self.sort(array, 0, len(array) - 1)

    def sort(self, array, low, high):
        if high <= low:
            return
        seperate = low + (high - low) // 2
        self.sort(array, low, seperate)
        self.sort(array, seperate + 1, high)
        self.merge(array, low, seperate, high)

    def merge(self, array, low, seperate, high):
        if not low <= seperate < high:
            return
        i = low
        j = seperate + 1

        for k in range(low, high + 1):
            self.list[k] = array[k]
        for k in range(low, high + 1):
            if i > seperate:
                array[k] = self.list[j]
                j += 1
            elif j > high:
                array[k] = self.list[i]
                i += 1
            elif self.less(self.list, j, i):
                array[k] = self.list[j]
                j += 1
            else:
                array[k] = self.list[i]
                i += 1

    def less(self, array, a, b):
        return array[a] < array[b]

    def exch(self, array, a, b):
        array[a], array[b] = array[b], array[a]


if __name__ == "__main__":
    from random import randint
    b = randint(0, 100)
    c = randint(0, 10)
    print(b)
    print(c)
    a = list(range(0,b,c))

    from random import shuffle

    print(a)
    shuffle(a)
    print(a)
    MergeUB(a)
    print(a)
