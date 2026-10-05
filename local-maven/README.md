# local-maven — 本地 maven 镜像

这个目录的作用是：让构建能在**没有 GitHub Packages 凭据**的机器上跑通。

⚠️ 这里的 `.jar` 都是**第三方模组**，不属于本项目，**已在 `.gitignore` 中排除，不随仓库分发**。
要自己构建，请按下面的路径把文件放进来（目录需要自己建）。

---

## 1. JackFredLib 0.10.6+26.3

26.3 的 JackFredLib 只发布在 **GitHub Releases**（GitHub Packages 需要鉴权）。

下载：<https://github.com/ponuing/JackFredLib/releases> → 取 `v0.10.6+26.3` 里的
`jackfredlib-0.10.6+26.3.jar`

放置路径：

```
local-maven/red/jackf/jackfredlib/jackfredlib/0.10.6+26.3/jackfredlib-0.10.6+26.3.jar
local-maven/red/jackf/jackfredlib/jackfredlib/0.10.6+26.3/jackfredlib-0.10.6+26.3.pom
```

对应的 `jackfredlib-0.10.6+26.3.pom` 内容：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
  <modelVersion>4.0.0</modelVersion>
  <groupId>red.jackf.jackfredlib</groupId>
  <artifactId>jackfredlib</artifactId>
  <version>0.10.6+26.3</version>
  <packaging>jar</packaging>
</project>
```

> 本项目只用到 `red.jackf.jackfredlib.client.api.gps.Coordinate` 这一个类，
> 所以放**聚合 jar** 或 release 里的 `jackfredlib-gps-*.jar` 都能编译通过。

---

## 2. Tweakeroo 0.30.0

从 CurseForge / Modrinth 下载 `tweakeroo-fabric-26.3-0.30.0.jar`，重命名后放置：

```
local-maven/maven/modrinth/tweakeroo/0.30.0/tweakeroo-0.30.0.jar
local-maven/maven/modrinth/tweakeroo/0.30.0/tweakeroo-0.30.0.pom
```

`tweakeroo-0.30.0.pom` 内容：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
  <modelVersion>4.0.0</modelVersion>
  <groupId>maven.modrinth</groupId>
  <artifactId>tweakeroo</artifactId>
  <version>0.30.0</version>
  <packaging>jar</packaging>
</project>
```

> **更省事的办法**：Modrinth 上有 `0.30.1`。把 `versions/26.3/gradle.properties` 里的
> `tweakeroo=0.30.0` 改成 `0.30.1`，然后删掉整个 `local-maven` 目录即可
> —— 那样就直接从 Modrinth maven 拉取了，不需要本地镜像。
