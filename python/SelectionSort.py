class Selection:
    def __init__(self, array):
        self.sort(array)

    def sort(self, array):
        for i in range(len(array)):
            min_index = i
            for j in range(i + 1, len(array)):
                if self.less(array, j, min_index):
                    min_index = j
            self.exch(array, min_index, i)
        return array

    def less(self, array, a, b):
        return array[a] < array[b]

    def exch(self, array, a, b):
        array[a], array[b] = array[b], array[a]


if __name__ == "__main__":
    a = [5, 1, 2, 3, 4, 6]
    Selection(a)
    print(a)
