# spring boot - react - mariadb

## 01. 2024-04-18
    - react-study project : STS4에서 생성
    - Spring boot dev, maria-driver, security, lombok
    - react-study에서 ReactStudyApplication 시작(8080)
    - react-study/src/main에서 
        npx create-react-app frontend
        cd frontend
        npm start(3000)
    - react - spring boot port 연동
    - frontend/package.json에서 아래 proxy 추가
    ----------------------------------------------------------------------------------------------
        "proxy": "http://localhost:3000",
        "scripts": {
            "start": "react-scripts start",
            "build": "react-scripts build",
            "test": "react-scripts test",
            "eject": "react-scripts eject"
        },
    ----------------------------------------------------------------------------------------------
    - build.gradle에서 마지막 부분에 아래 내용 추가
    ----------------------------------------------------------------------------------------------
        def frontendDir = "$projectDir/src/main/frontend"

        sourceSets {
            main {
                resources { srcDirs = ["$projectDir/src/main/resources"]
                }
            }
        }

        processResources { dependsOn "copyReactBuildFiles" }

        task installReact(type: Exec) {
            workingDir "$frontendDir"
            inputs.dir "$frontendDir"
            group = BasePlugin.BUILD_GROUP
            if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
                commandLine "npm.cmd", "audit", "fix"
                commandLine 'npm.cmd', 'install' }
            else {
                commandLine "npm", "audit", "fix" commandLine 'npm', 'install'
            }
        }

        task buildReact(type: Exec) {
            dependsOn "installReact"
            workingDir "$frontendDir"
            inputs.dir "$frontendDir"
            group = BasePlugin.BUILD_GROUP
            if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
                commandLine "npm.cmd", "run-script", "build"
            } else {
                commandLine "npm", "run-script", "build"
            }
        }

        task copyReactBuildFiles(type: Copy) {
            dependsOn "buildReact"
            from "$frontendDir/build"
            into "$projectDir/src/main/resources/static"
        }
    ----------------------------------------------------------------------------------------------
    - backend, frontend 재시작한 후
        http://localhost:3000, http://localhost:8080 접속하면 동일한 화면을 볼 수 있다.
    