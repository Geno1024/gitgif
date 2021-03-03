package com.geno1024.gitgif

import org.eclipse.jgit.lib.ObjectId
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.revwalk.RevWalk
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.treewalk.TreeWalk
import java.io.File

/**
 *
 * @author Geno1024 (Y. Z. Chen)
 * @date 2021-03-03 22:05:33
 * @param repoPath path to repo/.git
 */
class GitUtils(val repoPath: String)
{
    private val repo = FileRepositoryBuilder()
        .setGitDir(File(repoPath))
        .build()

    private val revs = RevWalk(repo)

    public val headId = repo.exactRef("HEAD").objectId

    public val head = idToCommit(headId)

    public fun idToCommit(id: ObjectId) = revs.parseCommit(id)

    public fun parentsOf(commit: RevCommit) = idToCommit(commit.id).parents.toList()

    public fun tree(commit: RevCommit): Map<RevCommit, List<RevCommit>> = mutableMapOf(head to parentsOf(head)).apply {
        while (values.flatten().any { it !in keys })
        {
            val toBeAdded = values.flatten().filter { it !in keys }[0]
            this[toBeAdded] = parentsOf(toBeAdded)
        }
    }
}
