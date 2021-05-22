package com.task.wordament.trie

/*Node for Trie DS: here key is the character/alphabet ,
 and parent is the parent node for the same node(node linked above to the node in the tree)*/
class TrieNode<Key>(var key: Key?, var parent: TrieNode<Key>?) {
    // info for children
    val children: HashMap<Key, TrieNode<Key>> = HashMap()

    // is last node
    var isLeaf = false
}