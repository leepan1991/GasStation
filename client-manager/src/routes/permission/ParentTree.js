import React, { PropTypes } from 'react'
import { Tree, Card } from 'antd'
const TreeNode = Tree.TreeNode;

const ParentTree = ({
  treeData,
  location,
  onParentSelect
}) => {
  const treeProps = {
    showLine: true,
    onSelect: (info) => {
      onParentSelect({parentId: info});
    }
  }

  const loop = data => data.map((item) => {
    if (item.children && item.children.length > 0) {
      return <TreeNode title={item.name} key={item.id} isLeaf={false}>{loop(item.children)}</TreeNode>
    }
    return <TreeNode title={item.name} key={item.id}></TreeNode>
  });

  return (
    <Card style={{minHeight: '500px'}}>
      <Tree {...treeProps}>
        {loop(treeData)}
      </Tree>
    </Card>
  )
}

ParentTree.propTypes = {
  treeData: PropTypes.array.isRequired,
  location: PropTypes.object,
  onParentSelect: PropTypes.func
}

export default ParentTree
