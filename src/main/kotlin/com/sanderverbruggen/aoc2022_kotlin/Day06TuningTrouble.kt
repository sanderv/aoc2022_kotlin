package com.sanderverbruggen.aoc2022_kotlin

import java.util.concurrent.ArrayBlockingQueue

class Day06TuningTrouble(val queueSize: Int) {
    private val queue = ArrayBlockingQueue<Char>(queueSize)

    fun solveLine(line: String): Int {
        queue.clear()
        line.forEachIndexed { i, char ->
            if (hasUniqueChars()) {
                return i
            } else {
                add(char)
            }
        }
        return -1
    }

    private fun add(char: Char) {
        if (queue.size == queueSize) {
            queue.remove()
        }
        queue.add(char)
    }

    private fun hasUniqueChars() = queue.distinct().size == queueSize

}

fun main() {
//    val solver = Day06TuningTrouble(4)
    val solver = Day06TuningTrouble(14)
    println(solver.solveLine("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    println(solver.solveLine("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    println(solver.solveLine("nppdvjthqldpwncqszvftbrmjlhg"))
    println(solver.solveLine("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
    println(solver.solveLine("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    println(
        solver.solveLine(
            "pjbjvjtjljplppjssvtvwtwptptztltbtrrjgrjrzrqrjrbrhbrhrlllbpbdbbzqqgsqshqssjjbsjbsbmbhhmchhrqrcqqbwbqwwqrrznnsbswwwdjwdwmmsvszzlbbgddbgdgfgttzjzrjzrjzzvrvqqgpqggtbgtgvvrhvhtvtjjbpjjfjhjbhbddbjjjmzmtmgmpgmmmljlnljjmpmbpmbmrrlhhlppdgdfgfbbqlbbtffjjgvvnpvpbpttqmmnhhgfgrrwhrrbnbznzccmbmvmmzszsvsbvbccsrslsjsbbtdtwtvttvpvzzvbzzwczwcczhzhwhjhjghgppgpgttdwdhwhphnppqmpqqhthcchdhmhnnbcnbcbbggbfblljttwsswspsggpjjzszcscsmcmdmnngzzhrzrbrhbhzbzvzgvgffnlnljlrrhchhsvhssmpmccncdnccgdgbglgtlggnllsvvpfvpfpbffsgglddjrrzzphhptprtppwrwffzllrbrmrpmpdmpdmpplpspcphcphhgmmqnmmvnmmdvmvsmmqjmmlmlbmlbmlltlptphpnncscbscbcggwhhgjgqjqmjmdjmdjdrrvgvvzlldgdnnvttmmpffdjjvvchcwwbhwbwzzlmlccrttcntccpcgccpgcpggdrrbtrthhlrrbqrrpspdsdldbldblbzbpbgggtngtgqqtwwdjjmmcrmcrcvclcddhllpzpdzzmccrtccfvffccfhccpscctbbbzqzvvllgwlgwgvwwjswjswsvvwhhvjvsjvjmmjhjrhrmrvrnrccmnmzzmdzzbtbvbqvvgzgcgvvvvlltvllbfbqqrppwhpwpffzddzdzwdzzrggmhmfhmhbbzjzsjzzhhjdhhdnndsnssnfffbmffwhwrhhmmbnnbrnrbrrtqtztnzzzzblbhbdhbbfmfqfmmsgszzvfzfmfwfnwfwggwngnqnwqnwqwvwqvwvdvrvjjfnjnmnfmmwzzltztjjqnnnmlmzlmmrcmclcqqhrhdhccdfdvfvccvtcvvdmmtmccwjjcbcrrjmrjrdrffgwwvbvlvsspwpsspzsschssmqsqmqmlqmmqgqfqcqjcjtthjttlddfvvwwjvvtpvvfsvffqnffznzqzszgzmmjttwztwzzhqqccqsqmqnmqqjhqhzhwhvhdddsndsdfftvffwlwnnmmdpmmnhhrqrrclcdlcddhcdcppgrprnpnptnpphgpbqfngdgzvgndwcgrwcsfmhzsvddhzbgjmvvdjjzswvgnpmvgdpwsgbgjzjpsrfdzdzjzzrpplbhsmgddqzjbdzdzltqqwqjzqvwfmcdppbdbprrwzhmnrqclzrnmdjnfbwmvdrwtpwvgscrqgpndqnzbjsbljcbthbpgdjdcdwfhpvjnbsfjdlrjldvvmtfdslrhlfwmvclqrljrqmmjgqfwmfgwdjzzptgcthvtgdswsqjrqvnzmtqldjjcqnfhtvbwhjqlvpptfwjrdpcwvzddgcjzvqbhtsnnnjqqmqlbgvqmvjhvvpbzcbdmhgmcjbfcccsvlzjztvjzrrlhtgwccdcgcptqlmdhmdhvqzfntbjqtsmvqgwsltqntgszllntrljfgfsghtbbcqrdgwqphmbqtzmjqccrgvqpqpchzjstdmmtvntwjqsbcqjgnhzlllcfbpgtgrhwwhqqdlgrlsbzbmchvjnsgpdnmqvtgwqjpgflqgfngjfcfwqzmvvgzmmhbgfnbzvzclwclqdcccgbrrzpwdtprgsvhbgsnbntgrvnzhrnzfzdmnlbnrbqvmjbwpgvjlhbcvsrlqmcsnlrvtfdwtvcbmlndgbctsnmtctjszlpddqmzbtphhhfznwbdfsgppmdmczmhmmrzpllfqqbgvlsrscpfgznhdhgrnnnvrchgvzlqbgvcfghjvlvrvpclfcshbmvglcfrjbzrbcjmjjrfgqthwfrqbgtjldmbnfwllspmwrvstvrltvrlvrtjvprgtgzjlrgclvjhqpfcwcdbdtzwdsdfrtsvtvgjmsszdfqlmhqqlzswjfndswlmhcrhglphvpnfjpbmggbwlmzjchpnrllbjpmgmzjjrqpqgsbrszqhdljcpnclvrvbntgtcdcmhtdhgslhpvdjpvrszfrjhsbvcvtfwvvgczprnpbhmnnlmctbtqdjspgvhvnhwvspwgnjvzllwlnjhfjwsslppmjbfbdnthcpzbcmnnbvhctgwgdvhvlrbltmdnlfcsncqgrmjprshdvvtvcccgzhszcjgczhmhtvmccjpchqshhdzjjhbfpzqdjszdhdvlmgctmwcjprwlsqbcqhlcrfdgnqzfdfvqslmqlppbsvbmjmfbrtdmpmtqvwvppcfzddjzhhzlrrnnhbrlhmzlqwftprfvctnfhfhfzrnrvggfqmqwcwszhtbfjncprgwcqbjlvtnrprlwwghswvprjmsbmqvwnnfggprndvshfvvwtrqjpwghgbppftgzhqjslfzhngwfsjnmjzdsjqgpmglwnjlcgmczgvndszrszcpnzqpbzjmgrfsbjlghwrbqsqdhlnhzsvsgbqhbcdffjlgrbdrrjvclzqpftlhdvvcrvlgvlpnjcqdcbdjtlwnldjhhhzrwsqlhlsztwrznfsszptlrhjmqwmnfwjtjwmmmtvwzhpmjgzgsscbddgvvhpcnhvnggzhbzvvjlmdftpbcsvtsttrvgghptmmcdclbdvmnsdntthfbdznbclwccnlzcvdwzrqgddjszvbdqcjppzrtpnrhfcvvwpjqczgqwzzzvzmlnlzqszvtllftthgwgftjzsndpzzcnqpcvmsdvvfrjdsvfclsqqhsjrrctfvdrlhfmhprjggdcmqrrbqtwnrllhhztvjgmzqszbvqfwsgllvhsvfrjffvdscwjzqlzlwdpgthddpgzjfdbdqpsnntwpslvsdpqfnsgcllszcjwvtqhwhpfrlfdgwrfmgfpjmvnstrmtfcvgwlqdfqvntltqtrmjjtwcthvwntqgvncssplnmvlnstlcphvlcmvjnstwldtntchcbmzmlzhgjfbrdlgzvqpgcndmfdnmcnwhmpdnpqstfddddcrpgrpfwfbzjqtnzwwqpzrqpmrjpfznrndfgwhtlvrcrphqfjzjbttwhgnsngqwvnsbvcqtjlmhvmnmnnmjcmlpnpgmrqsbmgljvsfqvrlljqzmzqqbgpvcrwdjmgsglssjswmnvtshhfqjhqmfmvcjwfpwsppgtrqsbhhcdljnjphnjszqpvdplbwzpwmmpwfhmhngtllzqvpmgdctmfqwwqjszssmjhwnrjdtmmvpdnwlqtcbpfcmwtbjmmsmmdpqgzdhsblgjmjbpzgqvqhnggtwmhztbbhlflllgwblncjjsngdgvsfdmsbsvlpnjjzqqbzhsqclmjnnmmwlpvtgwqmcgmrqdwdddlgbvhntbztbjnqhdlggnzwsdtdzprgddhtcttjrcpszgchtfwqjsdlnbntfwqpzpfsqrqjhthmcfszwtwcqwbvfzdnrrpmzjdrhsgmhfbsldvcrjdwvpqpszzlvbptljgvccqsdhhnztjpghbvhfptgplqdvldjzfthpspwvgljwnnndwrqzbrstnqbvrrcghssnrpvtrhmvcmbngwndzfswmgjwnnzqdcjhpthcgvthsnwqzrnzrvdjmctchhsbnrtvctzqfpcjhzmhnfjlqftbjztfbcppgmwvrzzrvlcpnpwwpvtcpdplrcfpgfqjtlfjtphhpcltwqcbqbznbtjrtdrpgtvzmgsclhpptrssqqbctdrftqzmwjmrmjtgmjmsnbnspjvcqpqnmgzgjrmfhghvsfsdqnbdjsbcpczsdswdcvhfzlgpzbtmztcnbpcvjnlcdmmlbtwzsfqtfnlrwjtwmgslcgptgbdsfwdhppvfwbbgdfdqtrbncbznmqtchzsdzlhlhjnnbpdvnnfjrdfbdqmvcb"
        )
    )
}