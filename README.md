\# 🚌 Grama-Yatri — Community Powered Rural Transit Intelligence System





\### 🚍 Smart Rural Mobility Platform for Real-Time Village Bus Tracking



</div>



\---



\# 📌 Overview



\*\*Grama-Yatri\*\* is an AI-assisted Android mobility platform designed to solve one of the biggest rural transportation problems:



> ❝ Unpredictable village bus timings leading to missed opportunities, wasted time, and economic inefficiency. ❞



The application creates a \*\*community-powered live transit system\*\* where passengers themselves update bus movement data in real-time.



Instead of depending on expensive GPS infrastructure, the system uses:



\- 📍 Passenger-generated live pings

\- ⏱️ Smart ETA calculations

\- 🔔 Real-time route alerts

\- ☁️ Firebase synchronization

\- 📶 Low-data optimized architecture



This transforms rural transportation into a predictable and efficient mobility ecosystem.



\---



\# 🚨 Problem Statement



In many villages:



\- Passengers miss buses by minutes

\- Next buses may arrive after several hours

\- No official live tracking systems exist

\- Rural workers and students lose valuable time daily



Traditional transport apps fail because:



| Existing Systems | Rural Reality |

|---|---|

| Require GPS-enabled fleets | Most village buses lack GPS |

| Depend on centralized infrastructure | Rural routes are decentralized |

| High internet usage | Connectivity is limited |

| Urban-focused mobility | Villages are ignored |



\---



\# 💡 Solution Vision



Grama-Yatri introduces a:



\# \*\*“Crowdsourced Rural Transit Intelligence Network”\*\*



Passengers collaboratively update the bus location by pressing:



\- ✅ “Bus Just Passed Me”

\- ✅ “I Am On The Bus”

\- ✅ “Bus Reached This Stop”



The app instantly:



1\. Updates Firebase Realtime Database

2\. Calculates ETA for upcoming villages

3\. Broadcasts live updates to all users

4\. Sends alerts and notifications



\---



\# ✨ Core Features



\## 🚍 Live Bus Tracking

\- Real-time passenger-generated tracking

\- Instant synchronization using Firebase

\- Live route movement updates



\## ⏱️ Smart ETA Prediction

\- Calculates expected arrival times

\- Uses average travel time logic

\- Dynamic route timeline updates



\## 🛣️ Route Timeline Visualization

\- Vertical route stepper/timeline

\- Current bus position indicators

\- Stop-wise ETA visualization



\## 🔔 Emergency \& Route Alerts

\- Bus cancellation reporting

\- Delay notifications

\- Community-generated transit alerts



\## 👥 Multi-Role System

Supports:

\- Passenger

\- Conductor

\- Admin



\## 📶 Low Data Optimized

Designed specifically for:

\- Rural internet conditions

\- Low-end Android devices

\- Limited connectivity zones



\## 🔥 Firebase Realtime Sync

Instant updates across all users on the route.



\---



\# 🧠 System Architecture



```text

&#x20;               ┌───────────────────────┐

&#x20;               │     Android App       │

&#x20;               │  Jetpack Compose UI   │

&#x20;               └──────────┬────────────┘

&#x20;                          │

&#x20;                          ▼

&#x20;               ┌───────────────────────┐

&#x20;               │      ViewModels       │

&#x20;               │   State Management    │

&#x20;               └──────────┬────────────┘

&#x20;                          │

&#x20;                          ▼

&#x20;               ┌───────────────────────┐

&#x20;               │     Repositories      │

&#x20;               │ Firebase + Local DB   │

&#x20;               └──────────┬────────────┘

&#x20;                          │

&#x20;         ┌────────────────┴────────────────┐

&#x20;         ▼                                 ▼

&#x20;┌───────────────────┐           ┌───────────────────┐

&#x20;│ Firebase Realtime │           │   Room Database   │

&#x20;│     Database      │           │ Offline Caching   │

&#x20;└───────────────────┘           └───────────────────┘

```



\---



\# 🏗️ Tech Stack



| Layer | Technology |

|---|---|

| Language | Kotlin |

| UI Framework | Jetpack Compose |

| Architecture | MVVM |

| Backend | Firebase Realtime Database |

| Authentication | Firebase Auth |

| Notifications | Firebase Cloud Messaging |

| Local Storage | Room Database |

| Async Handling | Kotlin Coroutines |

| Dependency Management | Gradle Kotlin DSL |



\---



\# 📱 Application Modules



| Module | Description |

|---|---|

| Authentication | Login \& Account Management |

| Route Tracking | Live route monitoring |

| ETA Engine | Time prediction system |

| Alerts System | Emergency \& cancellation alerts |

| Passenger Dashboard | Main mobility interface |

| Admin Dashboard | Analytics \& monitoring |

| Fleet Monitoring | Bus tracking administration |

| Wallet System | Digital mobility transactions |

| QR Ticketing | Smart ticket generation |

| Notification Engine | Real-time push alerts |



\---



\# 🔄 User Flow



```text

Passenger spots bus

&#x20;       │

&#x20;       ▼

Presses "Ping Bus"

&#x20;       │

&#x20;       ▼

Firebase updates route state

&#x20;       │

&#x20;       ▼

ETA recalculated instantly

&#x20;       │

&#x20;       ▼

All users receive updated timings

```



\---



\# 📊 Key Functionalities



\## Passenger Features

\- Live route tracking

\- ETA monitoring

\- Smart notifications

\- Saved routes

\- Ticket history



\## Admin Features

\- Route analytics

\- Fleet monitoring

\- Passenger statistics

\- Emergency broadcasts



\## Conductor Features

\- Live location sharing

\- Passenger coordination

\- Trip status updates



\---



\# 🧩 Database Design Overview



\## Firebase Collections / Nodes



| Node | Purpose |

|---|---|

| routes | Route information |

| live\_bus\_locations | Active bus positions |

| alerts | Emergency notifications |

| passengers | Passenger profiles |

| tickets | Ticket records |

| trips | Journey history |



\---



\# ⚡ Real-Time ETA Logic



The ETA engine works using:



```text

Current Bus Stop

&#x20;       +

Average Travel Time Between Stops

&#x20;       +

Realtime Passenger Pings

&#x20;       =

Predicted Arrival Time

```



This enables near real-time village bus forecasting.



\---



\# 🔐 Security \& Reliability



\- Firebase Authentication

\- Role-based access

\- Offline persistence

\- Local caching with Room

\- Real-time synchronization

\- Fault-tolerant architecture



\---



\# 🎯 Impact Goals



\## 🚶 Efficient Mobility

Reduce wasted waiting time at bus stops.



\## 💼 Economic Connectivity

Help workers reach jobs on time.



\## 🎓 Educational Access

Support students dependent on village transport.



\## 🌍 Rural Digital Inclusion

Bring smart mobility technology to villages.



\---



\# 📸 Major Screens



\- Splash Screen

\- Onboarding

\- Passenger Dashboard

\- Live Tracking Screen

\- Route Timeline

\- Alerts Screen

\- Admin Analytics

\- QR Scanner

\- Wallet Screen



\---



\# 🧪 Success Criteria



✔ Live ETA updates instantly for all users  

✔ Route timeline updates in real-time  

✔ Alert system works across devices  

✔ Low-bandwidth optimization achieved  

✔ Community-powered reports visible publicly  



\---



\# 🚀 Future Enhancements



\- AI-based traffic prediction

\- ML-powered route optimization

\- Voice assistant support

\- Kannada regional language AI

\- Offline-first synchronization

\- GPS + Crowdsourced hybrid tracking



\---



\# 📂 Project Structure



```text

GramaYatri/

│

├── app/

│   ├── src/main/java/com/example/gramayatri/

│   │   ├── auth/

│   │   ├── data/

│   │   ├── navigation/

│   │   ├── services/

│   │   ├── ui/

│   │   ├── utils/

│   │   ├── viewmodel/

│   │   └── core/

│   │

│   └── res/

│

├── gradle/

├── build.gradle.kts

└── settings.gradle.kts

```



\---



\# 👨‍💻 Development Approach



This project was developed using:



\- Android Studio

\- Kotlin + Jetpack Compose

\- Firebase Ecosystem

\- Modern Android Architecture

\- GenAI-assisted productivity workflows



\---



\# 📈 Performance Goals



| Goal | Target |

|---|---|

| App Launch Time | < 3 sec |

| ETA Sync Delay | < 2 sec |

| Low Data Usage | Optimized |

| Offline Capability | Supported |

| Real-time Accuracy | High |



\---



\# 🤝 Community Driven Mobility



Grama-Yatri proves that:



> Rural mobility problems can be solved through collective intelligence and lightweight digital infrastructure.



\---



\# 📜 License



This project is developed for educational, innovation, and social impact purposes.



\---



\# 🙌 Acknowledgements



Special thanks to:



\- Rural communities

\- Open-source Android ecosystem

\- Firebase platform

\- Jetpack Compose community

\- AI-assisted development tools



\---



<div align="center">



\## 🌾 “Smart Mobility for Every Village”



\### Built with ❤️ using Android + Firebase + GenAI



</div>

