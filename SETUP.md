# Setup Guide — OOP-Depaul

Everything you need to go from a fresh clone to running any code sample in SE 450 / SE 350.

---

## 1. Prerequisites

| Tool | Version | Notes |
|---|---|---|
| **JDK** | **25 LTS** or later | Eclipse Temurin recommended: <https://adoptium.net/temurin/releases/?version=25> |
| **Apache Maven** | 3.8.6 or later (3.9.x / 4.0.0 preferred) | <https://maven.apache.org/download.cgi> |
| **Git** | any recent version | <https://git-scm.com/> |
| **IDE** | IntelliJ IDEA (Community or Ultimate) **or** VS Code + Extension Pack for Java | Pick one |

Verify after installing:

```bash
java --version    # should print "openjdk 25..." or later
mvn --version     # should print "Apache Maven 3.8.6..." or later, Java version: 25
git --version
```

If `mvn --version` shows a Java older than 25, set `JAVA_HOME` to your JDK 25 install and restart your shell.

---

## 2. Clone and build

```bash
git clone https://github.com/Alizadeh-DePaul/OOP-Depaul.git
cd OOP-Depaul
mvn clean compile
```

Expected: `BUILD SUCCESS` with `[debug release 25]` in the compiler log.

To produce the jar:

```bash
mvn package
# target/OOP-DePaul-2.0.0-SNAPSHOT.jar
```

---

## 3. Running a demo

### From the IDE (most common)

Open any `.java` file that has a `public static void main(String[] args)` method and click the green ▶ in the gutter next to `main`. Every sample in this repo is designed to be runnable this way.

### From the command line

```bash
mvn exec:java -Dexec.mainClass=interactiveOopCodes.oopPrinciples.polymorphism.downcastingDemo.DowncastingDemo
```

Replace the fully qualified class name with whichever demo you want to run.

---

## 4. IntelliJ IDEA — one-time setup

Do this **once per machine**, not once per project. It takes 30 seconds.

### 4.0. First-time project open

`File → Open…` → select the `OOP-Depaul` folder (not a single file inside). IntelliJ detects `pom.xml` and shows a banner at the top of the editor: **"Maven build script detected — Load Maven project"**. Click **Load**.

Watch the progress bar at the bottom-right of the window — it's downloading dependencies (Lombok, JUnit, TestNG, annotations) and indexing sources. Wait until it goes silent (usually 30–90 seconds on a fresh clone). If you don't see the banner, right-click `pom.xml` in the Project view → `Maven → Reload Project`.

Once indexing is done, IntelliJ has generated a correct `.idea/` folder locally from the `pom.xml` — source roots, output dirs, and dependencies all match the pom. That config is **not** committed to the repo (`.idea/` is gitignored), so every student gets the same clean setup on first open.

### 4.1. Point the project at JDK 25

`File → Project Structure → Project → SDK` → select JDK 25 (add a new one via "Download JDK…" if needed).

### 4.2. Silence the JDK 22+ native-access warnings

If you run a program and see red text like:

```
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::load has been called by
         org.intellij.rt.execution.application.AppMain in an unnamed module
```

— those are **not** bugs in your code. They come from IntelliJ's own runtime wrapper `AppMain` (in `idea_rt.jar`), which loads a small native library called `breakgen` to enable the IDE's **Stop** button to send `Ctrl+Break` for clean shutdowns. JDK 22+ flags that as a restricted-method call; JDK 27+ will block it outright.

**Permanent fix — disable the wrapper entirely:**

1. `Help → Edit Custom VM Options…`
2. Add this line and save:

   ```
   -Didea.no.launcher=true
   ```

3. Restart IntelliJ.

That's it. `AppMain` never gets injected, no native library loads, the warning cannot appear on any current or future JDK.

**Trade-off (almost never noticeable):** the red **Stop** button now hard-kills the JVM instead of sending Ctrl+Break. Shutdown hooks and `finally` blocks after `System.exit`-less termination won't run. For the short demo programs in this course, that's fine.

---

## 5. VS Code — zero manual setup

1. Install the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) (Microsoft).
2. Open this folder — VS Code prompts to import the Maven project.
3. Done. `.vscode/settings.json` is already committed to this repo with the right VM args, encoding, and save actions. Click the **Run** codelens above any `main()`.

Nothing else to configure.

---

## 6. Repository layout

```
src/main/java/
├── oopBasics1/                          Lecture code — week 1–2 classes
├── oopBasics2/                          Lecture code — week 3–4 access / init
├── oopPrinciples/                       Lecture code — inheritance, polymorphism, abstraction
├── DesignPatterns/                      Lecture code — Singleton, Composite, Adapter, …
│
└── interactiveOopCodes/                 Samples backing https://oop.depaul.app
    └── {module}/                        fundamentals, oopPrinciples, uml, …
        └── {topic}/                     oopBuildingBlocks, classInternals, polymorphism, …
            └── {demo}/                  student, downcastingDemo, methodOverloading, …
                └── DemoClass.java       one demo per subpackage

src/test/java/                           JUnit 5 / TestNG tests (when added)
pom.xml                                  Maven build
.vscode/                                 Committed VS Code settings
SETUP.md                                 This file
```

**Why the per-demo subpackages under `interactiveOopCodes/`?**
Every demo ships with self-contained helper classes (`Shape`, `Student`, `Animal`, …). Without per-demo isolation, sibling demos with duplicate helper names would collide at compile time. One subpackage per demo file = no collisions, each demo stays standalone and runnable from the gutter.


## 7. Troubleshooting

| Symptom | Fix |
|---|---|
| `mvn --version` shows Java 11 / 17 / 21 | Install JDK 25, set `JAVA_HOME` to its install path, restart shell. |
| Maven error: *"Detected JDK Version: 1.x.y is not in the allowed range [25,)"* | Enforcer is doing its job — install JDK 25+. |
| Red `WARNING: restricted method` in IntelliJ run output | You skipped §4.2. Add `-Didea.no.launcher=true` in Help → Edit Custom VM Options. |
| IntelliJ grey folder icon on a new demo package | Folder name contains a hyphen — rename to camelCase (see §7). |
| `mvn compile` fails with *"package does not match filesystem path"* | Package declaration in the `.java` file doesn't match its directory. Fix the `package` line. |
| VS Code shows "Java project not found" | Run *Command Palette → Java: Clean Java Language Server Workspace*, reload window. |

---

## 9. Useful commands cheat sheet

```bash
mvn clean                                       # wipe target/
mvn compile                                     # compile only main sources
mvn test                                        # run JUnit 5 / TestNG tests
mvn package                                     # build the jar
mvn exec:java -Dexec.mainClass=<FQCN>           # run any main() from CLI
mvn enforcer:enforce                            # verify JDK and Maven versions
```
