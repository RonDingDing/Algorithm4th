class Bubble:
    def __init__(self, array):
        self.sort(array)

    def sort(self, array):
        for i in range(len(array), 0, -1):
            for j in range(i - 1):
                if self.less(array, j + 1, j):
                    self.exch(array, j, j + 1)

        return array

    def less(self, array, a, b):
        return array[a] < array[b]

    def exch(self, array, a, b):
        array[a], array[b] = array[b], array[a]


if __name__ == "__main__":
    from random import shuffle, randint

    a = list(range(randint(1, 20)))

    shuffle(a)
    Bubble(a)
    print(a)
