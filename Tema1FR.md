# RESUMEN CAPÍTULO 1 KUROSE

## OVERVIEW OF THE INTERNET AND NETWORKING PROTOCOLS
End systems are connected together by a network of communication links and packet switches.
There are many types of communication links, which are made up of different types of physical media, including coaxial
cable, copper wire, optical fiber, and radio spectrum. Different links can transmit
data at different rates, with the transmission rate of a link measured in bits/second.
When one end system has data to send to another end system, the sending end sys-
tem segments the data and adds header bytes to each segment. The resulting pack-
ages of information, known as packets in the jargon of computer networks, are then
sent through the network to the destination end system, where they are reassembled
into the original data.

A packet switch takes a packet arriving on one of its incoming communication
links and forwards that packet on one of its outgoing communication links. Packet
switches come in many shapes and flavors, but the two most prominent types in
today’s Internet are routers and link-layer switches. Both types of switches for-
ward packets toward their ultimate destinations. Link-layer switches are typically
used in access networks, while routers are typically used in the network core. The
sequence of communication links and packet switches traversed by a packet from
the sending end system to the receiving end system is known as a route or path
through the network.

Packet-switched networks (which transport packets) are in many ways simi-
lar to transportation networks of highways, roads, and intersections: packets are analogous to trucks, com-
munication links are analogous to highways and roads, packet switches are anal-
ogous to intersections, and end systems are analogous to buildings.

End systems access the Internet through Internet Service Providers (ISPs),
including residential ISPs such as local cable or telephone companies; corporate
ISPs; university ISPs; and ISPs that provide WiFi access in airports, hotels, coffee
shops, and other public places. Each ISP is in itself a network of packet switches
and communication links. ISPs provide a variety of types of network access to the
end systems, including residential broadband access such as cable modem or DSL,
high-speed local area network access, wireless access, and 56 kbps dial-up modem
access. ISPs also provide Internet access to content providers, connecting Web
sites directly to the Internet. The Internet is all about connecting end systems to
each other, so the ISPs that provide access to end systems must also be intercon-
nected. These lower-tier ISPs are interconnected through national and interna-
tional upper-tier ISPs such as Level 3 Communications, AT&T, Sprint, and NTT.
An upper-tier ISP consists of high-speed routers interconnected with high-speed
fiber-optic links. Each ISP network, whether upper-tier or lower-tier, is managed
independently, runs the IP protocol, and conforms to certain naming
and address conventions.

End systems, packet switches, and other pieces of the Internet run protocols
that control the sending and receiving of information within the Internet. The
Transmission Control Protocol (TCP) and the Internet Protocol (IP) are two of
the most important protocols in the Internet. The IP protocol specifies the format of
the packets that are sent and received among routers and end systems. The Internet’s
principal protocols are collectively known as TCP/IP.

we can also describe the Internet as
an infrastructure that provides services to applications.The
applications are said to be distributed applications, since they involve multiple
end systems that exchange data with each other. Importantly, Internet applications
run on end systems—they do not run in the packet switches in the network core.
Although packet switches facilitate the exchange of data among end systems, they
are not concerned with the application that is the source or sink of data.

End systems attached to the Internet provide an Application Programming
Interface (API) that specifies how a program running on one end system asks
the Internet infrastructure to deliver data to a specific destination program run-
ning on another end system. This Internet API is a set of rules that the sending
program must follow so that the Internet can deliver the data to the destination
program.

A network protocol is similar to a human protocol, except that the entities exchang-
ing messages and taking actions are hardware or software components of some
device. All activity in the Internet that involves two or more communicating remote
entities is governed by a protocol. For example, hardware-implemented protocols in
two physically connected computers control the flow of bits on the “wire” between
the two network interface cards; congestion-control protocols in end systems con-
trol the rate at which packets are transmitted between sender and receiver; protocols
in routers determine a packet’s path from source to destination.

*A protocol defines the format and the order of messages exchanged between
two or more communicating entities, as well as the actions taken on the trans-
mission and/or receipt of a message or other event.*

## COMPONENTS OF A COMPUTER NETWORK

### Home Access: DSL, Cable, FTTH, Dial-Up, and Satellite

Today, the two most prevalent types of broadband residential access are digital
subscriber line (DSL) and cable. A residence typically obtains DSL Internet access
from the same local telephone company (telco) that provides its wired local phone
access. Thus, when DSL is used, a customer’s telco is also its ISP. Each customer’s
DSL modem uses the existing telephone line (twisted-pair copper wire) to exchange data with a digi-
tal subscriber line access multiplexer (DSLAM) located in the telco’s local central
office (CO). The home’s DSL modem takes digital data and translates it to high-
frequency tones for transmission over telephone wires to the CO; the analog signals
from many such houses are translated back into digital format at the DSLAM.
The residential telephone line carries both data and traditional telephone sig-
nals simultaneously, which are encoded at different frequencies:
• A high-speed downstream channel, in the 50 kHz to 1 MHz band
• A medium-speed upstream channel, in the 4 kHz to 50 kHz band
• An ordinary two-way telephone channel, in the 0 to 4 kHz band

On the customer side, a splitter separates the data and telephone sig-
nals arriving to the home and forwards the data signal to the DSL modem. On the
telco side, in the CO, the DSLAM separates the data and phone signals and sends
the data into the Internet. Hundreds or even thousands of households connect to a
single DSLAM.

Because the downstream and upstream rates are different, the access is
said to be asymmetric. The actual downstream and upstream transmission rates
achieved may be less than the rates noted above, as the DSL provider may purpose-
fully limit a residential rate when tiered service (different rates, available at differ-
ent prices) are offered, or because the maximum rate can be limited by the distance
between the home and the CO, the gauge of the twisted-pair line and the degree of
electrical interference. Engineers have expressly designed DSL for short distances
between the home and the CO; generally, if the residence is not located within 5 to 10
miles of the CO, the residence must resort to an alternative form of Internet access.

While DSL makes use of the telco’s existing local telephone infrastructure,
cable Internet access makes use of the cable television company’s existing cable
television infrastructure. A residence obtains cable Internet access from the same
company that provides its cable television.

Cable internet access requires special modems, called cable modems. As with a
DSL modem, the cable modem is typically an external device and connects to the
home PC through an Ethernet port. At the cable head end, the cable modem termination system (CMTS)
serves a similar function as the DSL network’s DSLAM—turning the analog signal
sent from the cable modems in many downstream homes back into digital format.
Cable modems divide the HFC network into two channels, a downstream and an
upstream channel. As with DSL, access is typically asymmetric, with the down-
stream channel typically allocated a higher transmission rate than the upstream
channel. As in the case of DSL networks, the maximum
achievable rate may not be realized due to lower contracted data rates or media
impairments.

One important characteristic of cable Internet access is that it is a shared
broadcast medium. In particular, every packet sent by the head end travels down-
stream on every link to every home and every packet sent by a home travels on the
upstream channel to the head end. For this reason, if several users are simultane-
ously downloading a video file on the downstream channel, the actual rate at which
each user receives its video file will be significantly lower than the aggregate cable
downstream rate. Because the upstream channel is also shared, a distributed
multiple access protocol is needed to coordinate transmissions and avoid collisions.

There are several competing technologies for optical distribution from the
CO to the homes. The simplest optical distribution network is called direct fiber,
with one fiber leaving the CO for each home. More commonly, each fiber leav-
ing the central office is actually shared by many homes; it is not until the fiber
gets relatively close to the homes that it is split into individual customer-specific
fibers. There are two competing optical-distribution network architectures that
perform this splitting: active optical networks (AONs) and passive optical net-
works (PONs). AON is essentially switched Ethernet.

PON-- Each home has an optical network terminator (ONT), which is connected by dedicated optical
fiber to a neighborhood splitter. The splitter combines a number of homes (typically
less than 100) onto a single, shared optical fiber, which connects to an optical line
terminator (OLT) in the telco’s CO. The OLT, providing conversion between optical
and electrical signals, connects to the Internet via a telco router. In the home, users
connect a home router (typically a wireless router) to the ONT and access the Inter-
net via this home router. In the PON architecture, all packets sent from OLT to the
splitter are replicated at the splitter.

FTTH can potentially provide Internet access rates in the gigabits per second
range. However, most FTTH ISPs provide different rate offerings, with the higher
rates naturally costing more money.

Two other access network technologies are also used to provide Internet access
to the home. In locations where DSL, cable, and FTTH are not available (e.g., in
some rural settings), a satellite link can be used to connect a residence to the Inter-
net at speeds of more than 1 Mbps. Dial-up access over traditional phone lines is based on the same
model as DSL—a home modem connects over a phone line to a modem in the ISP.
Compared with DSL and other broadband access networks, dial-up access is excru-
ciatingly slow at 56 kbps.

### Ethernet and WiFi

On corporate and university campuses, and increasingly in home settings, a local area
network (LAN) is used to connect an end system to the edge router. Although there
are many types of LAN technologies, Ethernet is by far the most prevalent access
technology in corporate, university, and home networks.

Ethernet users use twisted-pair copper wire to connect to an Ethernet switch.
The Ethernet switch, or a network of such interconnected switches, is then
in turn connected into the larger Internet.
In a wireless LAN setting, wireless users transmit/receive pack-
ets to/from an access point that is connected into the enterprise’s network (most
likely including wired Ethernet), which in turn is connected to the wired Internet. A
wireless LAN user must typically be within a few tens of meters of the access point.
Many homes combine broadband residential access
(that is, cable modems or DSL) with these inexpensive wireless LAN technologies to
create powerful home networks.

Telecommunications companies have made enormous investments in so-called
third-generation (3G) wireless, which provides packet-switched wide-area wireless
Internet access at speeds in excess of 1 Mbps. But even higher-speed wide-area
access technologies —a fourth-generation (4G) of wide-area wireless networks— are
already being deployed. LTE (Long-Term Evolution) has its roots in 3G technology, and can potentially
achieve rates in excess of 10 Mbps. Unlike WiFi, a user need only be within a few tens of kilometers (as
opposed to a few tens of meters) of the base station.

In brief, HFC uses a combination of fiber cable and coaxial cable, DSL and Ethernet use
copper wire, and mobile access networks use the radio spectrum.

### Physical media

A bit, when traveling from source to destination, passes through a series
of transmitter-receiver pairs. For each transmitter-receiver pair, the bit is sent by
propagating electromagnetic waves or optical pulses across a physical medium.
The physical medium can take many shapes and forms and does not have to be of
the same type for each transmitter-receiver pair along the path. Examples of physi-
cal media include twisted-pair copper wire, coaxial cable, multimode fiber-optic
cable, terrestrial radio spectrum, and satellite radio spectrum. Physical media fall
into two categories: guided media and unguided media. With guided media, the
waves are guided along a solid medium, such as a fiber-optic cable, a twisted-pair
copper wire, or a coaxial cable. With unguided media, the waves propagate in the
atmosphere and in outer space, such as in a wireless LAN or a digital satellite
channel.

+ Twisted-Pair Copper Wire: It is the least expensive and most commonly used guided transmission medium.
For over a hundred years it has been used by telephone
networks. In fact, more than 99 percent of the wired connections from the tele-
phone handset to the local telephone switch use twisted-pair copper wire. Most of
us have seen twisted pair in our homes and work environments. Twisted pair con-
sists of two insulated copper wires, each about 1 mm thick, arranged in a regular
spiral pattern. The wires are twisted together to reduce the electrical interference
from similar pairs close by. Typically, a number of pairs are bundled together in a
cable by wrapping the pairs in a protective shield. A wire pair constitutes a single
communication link. Unshielded twisted pair (UTP) is commonly used for computer networks within a building, that is, for LANs.
The data rates that can be achieved depend on the thickness of the wire and the distance between transmitter and receiver.
When fiber-optic technology emerged in the 1980s, many people disparaged
twisted pair because of its relatively low bit rates. Some people even felt that fiber-
optic technology would completely replace twisted pair. But twisted pair did not
give up so easily. Modern twisted-pair technology, such as category 6a cable, can
achieve data rates of 10 Gbps for distances up to a hundred meters. In the end,
twisted pair has emerged as the dominant solution for high-speed LAN networking.

+ Coaxial Cable: Like twisted pair, coaxial cable consists of two copper conductors, but the two con-
ductors are concentric rather than parallel. With this construction and special insula-
tion and shielding, coaxial cable can achieve high data transmission rates. Coaxial
cable is quite common in cable television systems. Cable televi-
sion systems have recently been coupled with cable modems to provide residential
users with Internet access at rates of tens of Mbps. In cable television and cable
Internet access, the transmitter shifts the digital signal to a specific frequency band,
and the resulting analog signal is sent from the transmitter to one or more receivers.
Coaxial cable can be used as a guided shared medium. Specifically, a number of
end systems can be connected directly to the cable, with each of the end systems
receiving whatever is sent by the other end systems.

+ Fiber Optics: An optical fiber is a thin, flexible medium that conducts pulses of light, with each
pulse representing a bit. A single optical fiber can support tremendous bit rates, up
to tens or even hundreds of gigabits per second. They are immune to electromag-
netic interference, have very low signal attenuation up to 100 kilometers, and are
very hard to tap. These characteristics have made fiber optics the preferred long-
haul guided transmission media, particularly for overseas links. Many of the long-
distance telephone networks in the United States and elsewhere now use fiber optics
exclusively. Fiber optics is also prevalent in the backbone of the Internet. However,
the high cost of optical devices—such as transmitters, receivers, and switches—has
hindered their deployment for short-haul transport, such as in a LAN or into the
home in a residential access network.

+ Terrestrial Radio Channels: Radio channels carry signals in the electromagnetic spectrum. They are an attractive
medium because they require no physical wire to be installed, can penetrate walls,
provide connectivity to a mobile user, and can potentially carry a signal for long dis-
tances. The characteristics of a radio channel depend significantly on the propagation
environment and the distance over which a signal is to be carried. Environmental con-
siderations determine path loss and shadow fading (which decrease the signal strength
as the signal travels over a distance and around/through obstructing objects), multi-
path fading (due to signal reflection off of interfering objects), and interference (due
to other transmissions and electromagnetic signals).
Terrestrial radio channels can be broadly classified into three groups: those that
operate over very short distance (e.g., with one or two meters); those that operate in
local areas, typically spanning from ten to a few hundred meters; and those that
operate in the wide area, spanning tens of kilometers. Personal devices such as wire-
less headsets, keyboards, and medical devices operate over short distances; the
wireless LAN technologies use local-area radio channels;
the cellular access technologies use wide-area radio channels.

+ Satellite Radio Channels: A communication satellite links two or more Earth-based microwave transmitter/
receivers, known as ground stations. The satellite receives transmissions on one fre-
quency band, regenerates the signal using a repeater, and transmits
the signal on another frequency. Two types of satellites are used in communications:
geostationary satellites (The huge distance from ground station through satellite back
to ground station introduces a substantial signal propagation delay of 280 millisec-
onds. Nevertheless, satellite links, which can operate at speeds of hundreds of Mbps,
are often used in areas without access to DSL or cable-based Internet access) and low-earth orbiting (LEO) satellites (they may communicate with each other, as well as with ground stations. To provide continuous
coverage to an area, many satellites need to be placed in orbit. LEO satellite technology may be used
for Internet access sometime in the future).

### Packet switching

In a network application, end systems exchange messages with each other. Mes-
sages can contain anything the application designer wants. Messages may perform a
control function or can contain data. To send a message from a source to a destination end system,
the source breaks long messages into smaller chunks of data known as packets.
Between source and destination, each packet travels through communication links
and packet switches (for which there are two predominant types, routers and link-
layer switches). Packets are transmitted over each communication link at a rate
equal to the full transmission rate of the link. So, if a source end system or a packet
switch is sending a packet of L bits over a link with transmission rate R bits/sec, then
the time to transmit the packet is L/R seconds.

Most packet switches use store-and-forward transmission at the inputs to the
links. Store-and-forward transmission means that the packet switch must receive
the entire packet before it can begin to transmit the first bit of the packet onto the
outbound link.
Amount of time that elapses from when the source begins to send the
packet until the destination has received the entire packet: (Here we will ignore
propagation delay—the time it takes for the bits to travel across the wire at near the
speed of light) The source begins to transmit at time 0; at time L/R seconds, the source has transmitted the entire packet, and
the entire packet has been received and stored at the router. At time L/R seconds, since the router has just received the entire
packet, it can begin to transmit the packet onto the outbound link towards the desti-
nation; at time 2L/R, the router has transmitted the entire packet, and the entire
packet has been received by the destination. Thus, the total delay is 2L/R. If
the switch instead forwarded bits as soon as they arrive (without first receiving the
entire packet), then the total delay would be L/R since bits are not held up at
the router. But, as we will discuss in Section 1.4, routers need to receive, store, and
process the entire packet before forwarding.





















#
