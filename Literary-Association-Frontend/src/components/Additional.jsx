import React from 'react'
import WorkingPaper from './WorkingPaper'
import Comment from './Comment'
import PlagiarismPaper from './PlagiarismPaper'

const Additional = ({ isList, content, isPlagiarism }) => {
    let i = 0
    return (
        <>
            {
                content.map(contentObject => {
                    return !isList ?
                        <WorkingPaper key={++i} content={contentObject}/>
                        :
                        !isPlagiarism ? <Comment key={++i} content={contentObject}/> : <PlagiarismPaper key={++i} content={contentObject}/>
                })
            }
        </>
    )
}

export default Additional