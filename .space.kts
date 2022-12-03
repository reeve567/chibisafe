/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Build and push Docker") {
    host("Build artifacts and a Docker image") {
        // generate artifacts required for the image
        shellScript {
            content = """
                ./generateArtifacts.sh
            """
        }

        dockerBuildPush {
            file = "docker/chibisafe/Dockerfile"

            val spaceRepo = "xwy.registry.jetbrains.space/p/personal/containers/chibisafe"
            // image tags for 'docker push'
            tags {
                +"$spaceRepo:1.0.${"$"}JB_SPACE_EXECUTION_NUMBER"
                +"$spaceRepo:latest"
            }
        }
    }
}
