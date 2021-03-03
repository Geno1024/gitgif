package com.geno1024.gitgif

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.revwalk.DepthWalk
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.revwalk.RevWalk
import org.eclipse.jgit.storage.file.FileRepositoryBuilder


import java.io.File

object Main
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val repo = GitUtils("src/test/git/.git")
        println(repo.tree(repo.head))
    //        val repo = FileRepositoryBuilder()
//            .setGitDir(File("src/test/git/.git"))
//            .build()
//        val revs = DepthWalk.RevWalk(repo, 10)
//        val top = repo.findRef(repo.branch).objectId
//        println((revs.parseCommit(top))
        //        println(repo.objectDatabase.open(top).type)
    }
}
