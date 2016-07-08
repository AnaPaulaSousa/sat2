package gov.goias.sat2;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ederbd on 30/06/16.
 */
@XmlRootElement
@Data
public class GitRepositoryState {

    private static GitRepositoryState me;

    protected GitRepositoryState() throws IOException {

        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("git.properties"));

        this.tags = String.valueOf(properties.get("git.tags"));
        this.branch = String.valueOf(properties.get("git.branch"));
        this.dirty = String.valueOf(properties.get("git.dirty"));
        this.remoteOriginUrl = String.valueOf(properties.get("git.remote.origin.url"));

        this.commitId = String.valueOf(properties.get("git.commit.id")); // properties.get("git.commit.id.full") OR properties.get("git.commit.id") depending on your configuration
        this.commitIdAbbrev = String.valueOf(properties.get("git.commit.id.abbrev"));
        this.describe = String.valueOf(properties.get("git.commit.id.describe"));
        this.describeShort = String.valueOf(properties.get("git.commit.id.describe-short"));
        this.commitUserName = String.valueOf(properties.get("git.commit.user.name"));
        this.commitUserEmail = String.valueOf(properties.get("git.commit.user.email"));
        this.commitMessageFull = String.valueOf(properties.get("git.commit.message.full"));
        this.commitMessageShort = String.valueOf(properties.get("git.commit.message.short"));
        this.commitTime = String.valueOf(properties.get("git.commit.time"));
        this.closestTagName = String.valueOf(properties.get("git.closest.tag.name"));
        this.closestTagCommitCount = String.valueOf(properties.get("git.closest.tag.commit.count"));

        this.buildUserName = String.valueOf(properties.get("git.build.user.name"));
        this.buildUserEmail = String.valueOf(properties.get("git.build.user.email"));
        this.buildTime = String.valueOf(properties.get("git.build.time"));
        this.buildHost = String.valueOf(properties.get("git.build.host"));
        this.buildVersion = String.valueOf(properties.get("git.build.version"));

        if (this.remoteOriginUrl != null) {
            String gitUrl = this.remoteOriginUrl.substring(0, this.remoteOriginUrl.length() - 4);
            this.tagName = this.closestTagName != null && !"".equals(this.closestTagName) ? this.closestTagName : "v1.0.0";
            this.remoteTagUrl = gitUrl + "/tags/" + this.tagName;
            if (this.commitId != null) {
                this.remoteBuildUrl = gitUrl + "/commit/" + this.commitId;
            }
        }

    }

    public static GitRepositoryState getInstance() throws IOException {
        if (me == null) {
            me = new GitRepositoryState();
        }
        return me;
    }

    String tags;                    // =${git.tags} // comma separated tag names
    String branch;                  // =${git.branch}
    String dirty;                   // =${git.dirty}
    String remoteOriginUrl;         // =${git.remote.origin.url}
    String remoteTagUrl;
    String remoteBuildUrl;
    String tagName;

    String commitId;                // =${git.commit.id.full} OR ${git.commit.id}
    String commitIdAbbrev;          // =${git.commit.id.abbrev}
    String describe;                // =${git.commit.id.describe}
    String describeShort;           // =${git.commit.id.describe-short}
    String commitUserName;          // =${git.commit.user.name}
    String commitUserEmail;         // =${git.commit.user.email}
    String commitMessageFull;       // =${git.commit.message.full}
    String commitMessageShort;      // =${git.commit.message.short}
    String commitTime;              // =${git.commit.time}
    String closestTagName;          // =${git.closest.tag.name}
    String closestTagCommitCount;   // =${git.closest.tag.commit.count}

    String buildUserName;           // =${git.build.user.name}
    String buildUserEmail;          // =${git.build.user.email}
    String buildTime;               // =${git.build.time}
    String buildHost;               // =${git.build.host}
    String buildVersion;             // =${git.build.version}


}
