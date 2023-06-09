
## 流程图

![netty-pipeline.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fimages%2Fnetty-pipeline.png)


## 为什么使用双向链表而不是使用单向链表？

单向链表（Singly Linked List）和双向链表（Doubly Linked List）是两种常见的链表数据结构，它们之间的主要区别在于节点的链接方式和操作的灵活性。

1. 链接方式：

- 单向链表：每个节点只包含一个指向下一个节点的指针。节点只能从头到尾依次遍历，无法反向访问前一个节点。

- 双向链表：每个节点既包含一个指向下一个节点的指针，也包含一个指向前一个节点的指针。节点可以从头到尾或者从尾到头进行遍历，可以双向访问前一个和后一个节点。

1. 操作灵活性：

- 单向链表：由于只有单向连接，对于插入、删除、查找等操作，需要从头节点开始遍历，直到找到目标节点。删除节点时，需要找到目标节点的前一个节点来修改指针。

- 双向链表：由于双向连接，插入、删除、查找等操作更加灵活高效。对于插入和删除操作，只需修改目标节点的前后节点的指针即可，不需遍历整个链表。同时，反向遍历链表时无需重新遍历，可以直接根据前一个节点的指针前进。

1. 内存消耗：

- 单向链表：每个节点只需存储一个指针，相对于双向链表占用更少的内存空间。

- 双向链表：每个节点需要同时存储两个指针，相对于单向链表占用更多的内存空间。 综上所述，单向链表适用于简单的数据结构和操作，内存消耗较小。而双向链表由于具有双向访问的特性，在某些场景下能提供更高效的操作和灵活性。选择使用哪种链表结构应根据实际需求和场景来决定。