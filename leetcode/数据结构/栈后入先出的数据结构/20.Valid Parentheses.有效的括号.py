class Solution:
    def isValid(self, s: str) -> bool:
        if s == "":
            return True
        stack = []
        dic = {"(": ")", "[": "]", "{": "}"}

        for c in s:
            if c in dic.keys():
                stack.append(c)
            elif c in dic.values():
                if stack:
                    if c != dic[stack.pop()]:
                        return False
                else:
                    return False
        return not stack


if __name__ == "__main__":
    a = Solution()
    b = a.isValid("([]){}")
    print(b)
