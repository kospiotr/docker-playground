# docker-playground

The goal of this project is to demonstrate and learn how Jenkins with modern pipeline workflow and Docker can be used for setting up CI and CD process.

## Workflow stages

1) Initialize
  * extract version from `gradle.properties`
  * set up new version as follow:
    * strips `-SNAPSHOT`
    * for `master` branch adds suffix: `-${BUILD_ID}`, where `${BUILD_ID}` is Jenkins build number
    * for non `master` branch adds suffix: `-${BRANCH_NAME}`
   * Save new version back to `gradle.properties`
2) Build
 * execute: `gradle clean build buildDocker` - this triggers running all tests, building process and building docker image which is installed in local docker registry
 * register junit result
 * archives `jar` files
3) Tag
 * only for `master` branch
 * create SCM tag with new version
 * commit `gradle.properties` and push changes to tag
4) Deploy
 * only for non `master` branch
 * extract docker image name and version from `gradle.properties`
 * container name: "${imageName}_${BRANCH_NAME}
 * stop currently running containers
 * detect first available port from range `9000 - 9999`
 * run container based on collected data:
   * image name from `gradle.properties`
   * image version from `gradle.properties`
   * port: first available port from range `9000 - 9999`
   * container name
