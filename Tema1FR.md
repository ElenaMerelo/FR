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



















#
