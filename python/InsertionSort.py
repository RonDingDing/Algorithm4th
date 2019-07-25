class Insertion:
    def __init__(self, array):
        self.sort(array)

    def sort(self, array):
        for i in range(1, len(array)):
            for j in range(i, 0, -1):
                print(j - 1, j)
                if self.less(array, j, j - 1):
                    self.exch(array, j, j - 1)

    def less(self, array, a, b):
        return array[a] < array[b]

    def exch(self, array, a, b):
        array[a], array[b] = array[b], array[a]


if __name__ == "__main__":
    a = [5, 1, 2, 3, 4, 6]
    from random import shuffle

    shuffle(a)
    Insertion(a)
    print(a)
