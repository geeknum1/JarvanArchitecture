# JarvanArchitecture
借鉴 GoogleArchitectureDemo-master改造而来
主要架构以及语言 ：sophix+mvp+dagger2+rx全家桶+retrofit2+组件化通信arouter+kotlin

lib_common : 公共库，主要有各种base，各种ui组件，自定义组件，公用的Activity、公用的Fragment，和公用的utils等等。

module_girls : 妹子功能模块，可以在library和application之间切换，自己可以是一个app也可以成为别的app的一个组件模块。组件化编译时为app，反之为module。

module_news : 新闻功能模块，可以在library和application之间切换，自己可以是一个app也可以成为别的app的一个组件模块。组件化编译时为app，反之为module。

app: 组件化编译时 module_girls和module_news为app，所以不能把这两个作为module加进来编译，所以组件化编译时app要依赖lib_common库，反之就可以把 module_girls和module_news作为module加进来编译。

ARouter串联各个模块
使用ARouter来跳转Activity和获取Fragment，记得看之前别人的组件化结构文章，一直都在纠结Fragment的获取问题，我想说的是有了ARouter来获取Fragment不是超级简单么？

ARouter典型应用
从外部URL映射到内部页面，以及参数传递与解析
跨模块页面跳转，模块间解耦
拦截跳转过程，处理登陆、埋点等逻辑
跨模块API调用，通过控制反转来做组件解耦
组件化编译和非组件化编译切换
我们在工程根目录下的gradle.properties文件中加入一个Boolean类型的变量，通过修改这个变量来识别编译模式：

# 每次更改“isModule”的值后，需要点击 "Sync Project" 按钮
# isModule是“集成开发模式”和“组件开发模式”的切换开关
isModule=false
然后在 module_girls和module_news中的build.gradle文件中支持切换：

if (isModule.toBoolean()) {
    //组件化编译时为application
    apply plugin: 'com.android.application'
} else {
    //非组件化编译时为library
    apply plugin: 'com.android.library'
}

android {
    compileSdkVersion build_versions.target_sdk
    buildToolsVersion build_versions.build_tools

    defaultConfig {
        minSdkVersion build_versions.min_sdk
        targetSdkVersion build_versions.target_sdk
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"

        //ARouter
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [moduleName: project.getName()]
            }
        }
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    dataBinding {
        enabled = true
    }
    lintOptions {
        abortOnError false
    }
    sourceSets {
        main {
            if (isModule.toBoolean()) {
                //组件化编译时为app，在对应的AndroidManifest文件中需要写ndroid.intent.action.MAIN入口Activity
                manifest.srcFile 'src/main/module/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/main/AndroidManifest.xml'
                //集成开发模式下排除debug文件夹中的所有Java文件
                java {
                    //debug文件夹中放的是Application类，非组件化时不用有此类
                    exclude 'debug/**'
                }
            }
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    api project(':lib_common')
    implementation 'com.android.support:support-v4:26.1.0'
    annotationProcessor deps.arouter.compiler
}

上面看到了组件化和非组件化编译会有不用的AndroidManifest文件，组件化时需要debug文件夹下面的application类，非组件化时排除此文件夹。
module下的AndroidManifest文件是组件化app编译时的，写了MAIN入口Activity
dubug下是组件化app编译时的Application类，初始化作为一个app运行时需要的资源等等。在非组件化编译在build.gradle文件中排除debug文件夹的所以东西。
